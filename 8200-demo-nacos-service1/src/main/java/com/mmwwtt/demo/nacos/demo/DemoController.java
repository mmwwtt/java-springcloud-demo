package com.mmwwtt.demo.nacos.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("restTemplateLoadBalanced")
    private RestTemplate restTemplateLoadBalanced;

    @GetMapping("/discovery")
    public String nacosDiscovery() {
        return "Hello, Nacos!";
    }


    @GetMapping("/httpRequest")
    private String httpRequest() {
        //获得服务所有实例后，取一个
        List<ServiceInstance> instances = discoveryClient.getInstances("nacos2-services");
        ServiceInstance instance = instances.get(0);
        String url1 = "http://" + instance.getHost() + ":" + instance.getPort() + "/nacos2/demo/discovery";
        log.info("微服务http请求:" + url1);
        String str1 = restTemplate.getForObject(url1, String.class);

        //负载均衡的获取服务
        ServiceInstance choose = loadBalancerClient.choose("nacos2-services");
        String url2 = "http://" + choose.getHost() + ":" + choose.getPort() + "/nacos2/demo/discovery";
        log.info("微服务http请求-负载均衡:" + url2);
        String str2 = restTemplate.getForObject(url2, String.class);

        //@LoadBalanced注解内部实现负载均衡获取实例
        String url3 = "http://nacos2-services" + "/nacos2/demo/discovery";
        log.info("(注解负载均衡)远程请求商品的日志：" + url3);
        String str3 = restTemplateLoadBalanced.getForObject(url3, String.class);
        return str3;
    }
}
