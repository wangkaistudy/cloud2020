package com.clay.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerNacosOrderMain83 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerNacosOrderMain83.class, args);
    }
}
