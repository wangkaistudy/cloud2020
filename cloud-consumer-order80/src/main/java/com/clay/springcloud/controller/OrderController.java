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

    public static final String PAYMENT_ULR = "http://localhost:8001";


    @PostMapping("/consumer/payment/create")
    public CommonResult creawte(Payment payment) {
        return restTemplate.postForObject(PAYMENT_ULR + "/payment/create", payment, CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_ULR + "/payment/get/" + id, CommonResult.class);

    }

}
