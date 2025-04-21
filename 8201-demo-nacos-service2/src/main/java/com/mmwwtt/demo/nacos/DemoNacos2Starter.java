package com.mmwwtt.demo.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.mmwwtt.demo.common", "com.mmwwtt.demo.nacos"})
public class DemoNacos2Starter {
    public static void main(String[] args) {
        SpringApplication.run(DemoNacos2Starter.class, args);
    }
}
