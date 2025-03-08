package com.mmwwtt.serviceorder8001.feign;

import com.mmwwtt.common.entity.Product;
import com.mmwwtt.serviceorder8001.feign.fallback.ProductFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign微服务调用
 */
@FeignClient(value = "service-product-8003", fallback = ProductFeignClientFallBack.class)
public interface ProductFeignClient {
    //    @GetMapping("/get/{productId}")
//    Product getProductById(@PathVariable("productId") String productId, @RequestHeader String token,
//                           @RequestParam("number") String number, @RequestBody Object object);
    @GetMapping("/product/get/{productId}")
    Product getProductById(@PathVariable("productId") String productId);

    @GetMapping("/product/demo/seata")
    void demoSeata();
}
