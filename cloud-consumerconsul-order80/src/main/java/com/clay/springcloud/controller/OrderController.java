package com.clay.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {


    @Autowired
    private RestTemplate restTemplate;

    public static final String PAYMENT_ULR = "http://consul-provider-payment";



    @GetMapping("/consumer/payment/consul")
    public String payment (){
        String result = restTemplate.postForObject(PAYMENT_ULR+"/payment/consul",null,String.class);
        return result;
    }


}
