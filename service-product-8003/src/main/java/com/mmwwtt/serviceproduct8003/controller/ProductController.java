package com.mmwwtt.serviceproduct8003.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mmwwtt.common.entity.Product;
import com.mmwwtt.serviceproduct8003.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping("/get/{id}")
    @SentinelResource(value = "getProductById", fallback = "fallbackMethod")
    public Product getProductById(@PathVariable("id") String productId,
                                  HttpServletRequest request) {
        String xToken = request.getHeader("X-token");
        return productService.getById(productId);
    }

    public String fallbackMethod(Throwable ex) {
        return "Service is busy, please try again later.";
    }

    @GetMapping("/demo/seata")
    @GlobalTransactional(name = "order-service", rollbackFor = Exception.class)
    public void demoSeata() {
        try {
            log.info("seata - product 正常执行");
            throw new Exception();
        } catch (Exception e) {
            log.info("seata - product 发生回滚");
        }
    }
}
