package com.mmwwtt.serviceproduct8003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceProduct8003Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProduct8003Application.class, args);
    }

}
