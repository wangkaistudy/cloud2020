package com.clay.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class CloudStreamProviderMain8801 {


    public static void main(String[] args) {
        SpringApplication.run(CloudStreamProviderMain8801.class, args);
    }
}
