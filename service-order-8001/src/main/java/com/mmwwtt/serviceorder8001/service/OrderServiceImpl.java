package com.mmwwtt.serviceorder8001.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    

    @Override
    public void createOrder(String productId, String userId) {

    }

//    @Override
//    public Order createOrder(String productId, String userId) {
//        Order order = Order.getInstance();
//        order.setProductList(Arrays.asList(getProductFromRemote(productId)));
//        return order;
//    }

    /**
     * 从远程获取订单信息
     * 使用restTemplate调用微服务请求
     * 使用openFeign调用微服务请求
     *
     * @param productId
     * @return
     */
//    private Product getProductFromRemote(String productId) {
//
//        //使用openFeign调用微服务接口
//        Product product3 = productFeignClient.getProductById(productId);
//
//        //使用openFeign调用API接口
//        Product product4 = productAPIFeignClient.getProductById(productId);
//        return null;
//
//    }

}
