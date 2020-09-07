package com.clay.springcloud.controller;

import com.clay.springcloud.entities.CommonResult;
import com.clay.springcloud.entities.Payment;
import com.clay.springcloud.lb.LoadBanlancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private LoadBanlancer loadBanlancer;

    @Autowired
    private RestTemplate restTemplate;

    public static final String PAYMENT_ULR = "http://CLOUD-PROVIDER-PAYMENT";

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_ULR + "/payment/create", payment, CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_ULR + "/payment/get/" + id, CommonResult.class);

    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Integer id){

        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_ULR + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        }
        return CommonResult.fail();
    }

    @GetMapping("/consumer/payment/lb")
    public String lb(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        if(serviceInstances==null||serviceInstances.size()==0){
            return "失败";
        }
        ServiceInstance instance = loadBanlancer.instance(serviceInstances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+ "/payment/lb", String.class);

    }
}
