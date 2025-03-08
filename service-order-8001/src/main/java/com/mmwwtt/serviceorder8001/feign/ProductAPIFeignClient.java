package com.mmwwtt.serviceorder8001.feign;

import com.mmwwtt.common.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign第三方API,通过url调用
 */
@FeignClient(value = "product-api", url = "http://localhost:8003/product")
public interface ProductAPIFeignClient {

    @GetMapping("/get/{productId}")
    Product getProductById(@PathVariable("productId") String productId);
}
