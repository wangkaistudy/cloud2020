package com.clay.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient //使用zookeeper作为注册中心
public class PaymentMain8004 {


    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
