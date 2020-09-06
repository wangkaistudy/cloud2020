package com.clay.springcloud.service.iml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.clay.springcloud.entities.Payment;
import com.clay.springcloud.mapper.PaymentMapper;
import com.clay.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService{

}
