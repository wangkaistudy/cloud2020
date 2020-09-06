package com.clay.springcloud.controller;

import com.clay.springcloud.entities.CommonResult;
import com.clay.springcloud.entities.Payment;
import com.clay.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    public PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        boolean save = paymentService.save(payment);
        if (save){
            return CommonResult.succes("端口:"+serverPort);
        }
        return CommonResult.fail();
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Integer id){
        Payment payment = paymentService.getById(id);

        return CommonResult.succes("端口:"+serverPort,payment);
    }


}
