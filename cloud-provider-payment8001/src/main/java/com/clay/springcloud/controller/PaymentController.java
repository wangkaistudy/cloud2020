package com.clay.springcloud.controller;

import com.clay.springcloud.entities.CommonResult;
import com.clay.springcloud.entities.Payment;
import com.clay.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    public PaymentService paymentService;


    @PostMapping("/payment/create")
    public CommonResult create(Payment payment){
        boolean save = paymentService.save(payment);
        if (save){
            return CommonResult.succes();
        }
        return CommonResult.fail();
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Integer id){
        Payment payment = paymentService.getById(id);

            return CommonResult.succes(payment);
    }



}
