package com.mmwwtt.demo.nacos.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/discovery")
    public String nacosDiscovery() {
        return "Hello, Nacos!";
    }
}
