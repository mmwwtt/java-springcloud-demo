package com.mmwwtt.serviceorder8001.service;

import com.mmwwtt.common.entity.Order;
import com.mmwwtt.common.entity.Product;
import com.mmwwtt.serviceorder8001.feign.ProductAPIFeignClient;
import com.mmwwtt.serviceorder8001.feign.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private ProductAPIFeignClient productAPIFeignClient;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("restServiceTemplate")
    private RestTemplate restServiceTemplate;

    @Override
    public Order createOrder(String productId, String userId) {
        Order order = Order.getInstance();
        order.setProductList(Arrays.asList(getProductFromRemote(productId)));
        return order;
    }

    /**
     * 从远程获取订单信息
     * 使用restTemplate调用微服务请求
     * 使用openFeign调用微服务请求
     *
     * @param productId
     * @return
     */
    private Product getProductFromRemote(String productId) {
        //获得服务所有实例，每次取第一个实例的ip+端口调用(无负载均衡)
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product-8003");
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/get/" + productId;
        log.info("远程请求商品的日志：" + url);
        Product product = restTemplate.getForObject(url, Product.class);

        //负载均衡的获取实例ip+端口(轮训/随机等)
        ServiceInstance choose = loadBalancerClient.choose("service-product-8003");
        String url1 = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/get/" + productId;
        log.info("(负载均衡)远程请求商品的日志：" + url1);
        Product product1 = restTemplate.getForObject(url1, Product.class);

        //@LoadBalanced 注解实现负载均衡的获取实例ip+端口(轮训/随机等)
        String url2 = "http://service-product-8003" + "/product/get/" + productId;
        log.info("(注解负载均衡)远程请求商品的日志：" + url2);
        Product product2 = restServiceTemplate.getForObject(url2, Product.class);

        //使用openFeign调用微服务接口
        Product product3 = productFeignClient.getProductById(productId);

        //使用openFeign调用API接口
        Product product4 = productAPIFeignClient.getProductById(productId);
        return product;

    }

}
