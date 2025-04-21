package com.mmwwtt.serviceorder8001.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mmwwtt.serviceorder8001.config.OrderYml;
import com.mmwwtt.serviceorder8001.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RefreshScope  // 当前bean会动态从配置中心取
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Value("${order.timeout}")
    private String orderTimeout;

    @Value("${order.auto-confirm}")
    private String orderAutoConfirm;

    @Autowired
    private OrderYml orderYml;

    @GetMapping("/nacos/discovery/demo")

    public String nacosDiscovery() {
        return "Hello, Nacos!";
    }

    /**
     * 推荐使用 @ConfigurationProperties(prefix = "order") 来动态获取配置中心的配置
     *
     * @return
     */
    @GetMapping("/nacos/config/demo")
    public String nacosConfig() {
        String config1 = "orderTimeout: " + orderTimeout + "     " + "orderAutoConfirm: " + orderAutoConfirm;
        String config2 =
                "orderTimeout: " + orderYml.getTimeout() + "     " + "orderAutoConfirm: " + orderYml.getAutoConfirm();
        return config1 + "\n" + config2;
    }

//    @GetMapping("/demo/seata")
//    @GlobalTransactional(name = "order-service", rollbackFor = Exception.class)
//    public void demoSeata() {
//        log.info("seata - order 正常执行");
//        //使用openFeign调用接口
//        productFeignClient.demoSeata();
//    }

    //    @GetMapping("/create")
//    public Order createOrder(List<Product> productList, String userId) {
//        return new Order();
//    }

    /**
     * 根据资源名限流后的异常处理 (一般用在非controller的方法中)
     * 根据路径限流： 应用在controller方法中
     *
     * @param productId
     * @param userId
     * @return
     */
    @GetMapping("/create")
    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    public void createOrder(@RequestParam("productId") String productId, @RequestParam("userId") String userId) {
        orderService.createOrder(productId, userId);
    }

    //sentinel执行兜底回调
    public void createOrderFallback(String productId, String userId, BlockException e) {
        return;
    }
}
