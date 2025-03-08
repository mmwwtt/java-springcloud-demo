package com.mmwwtt.serviceorder8001.feign.fallback;

import com.mmwwtt.common.entity.Product;
import com.mmwwtt.serviceorder8001.feign.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 如果调用接口报错/超时后，则用兜底数据进行返回
 */
@Component
@Slf4j
public class ProductFeignClientFallBack implements ProductFeignClient {
    @Override
    public Product getProductById(String productId) {
        log.info("兜底回调");
        return null;
    }

    @Override
    public void demoSeata() {
        log.info("兜底回调");
    }
}
