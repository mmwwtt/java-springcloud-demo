package com.mmwwtt.servicegateway8101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceGateway8101Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceGateway8101Application.class, args);
    }

}
