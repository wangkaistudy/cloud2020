package com.clay.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService
{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----------paymentInfo_OK-----fallaback---";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----------paymentInfo_TimeOut-----fallaback---";
    }
}
