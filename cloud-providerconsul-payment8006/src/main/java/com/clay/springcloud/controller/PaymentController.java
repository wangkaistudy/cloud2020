package com.clay.springcloud.controller;

import com.clay.springcloud.entities.CommonResult;
import com.clay.springcloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {



    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/consul")
    public CommonResult paymentZk(){

            return CommonResult.succes("springcloud with consul 端口:"+serverPort);

    }




}
