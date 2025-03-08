package com.mmwwtt.serviceorder8001.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")
@Data
public class OrderYml {
    private String timeout;
    private String autoConfirm;
}
