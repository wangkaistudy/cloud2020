package com.clay.springcloud.controller;

import com.clay.springcloud.entities.CommonResult;
import com.clay.springcloud.entities.Payment;
import com.clay.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }



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
