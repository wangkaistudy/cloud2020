package com.clay.springcloud.controller;

import com.clay.springcloud.entities.CommonResult;
import com.clay.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {


    @Autowired
    private RestTemplate restTemplate;

    public static final String PAYMENT_ULR = "http://cloud-provider-payment";


    @GetMapping("/consumer/payment/zk")
    public CommonResult payment (){
        CommonResult result = restTemplate.postForObject(PAYMENT_ULR+"/payment/zk",null,CommonResult.class);
        return result;
    }


}
