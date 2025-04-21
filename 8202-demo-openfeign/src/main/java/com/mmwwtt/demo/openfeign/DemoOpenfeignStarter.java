package com.mmwwtt.demo.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoOpenfeignStarter {
    public static void main(String[] args) {
        SpringApplication.run(DemoOpenfeignStarter.class, args);
    }
}
