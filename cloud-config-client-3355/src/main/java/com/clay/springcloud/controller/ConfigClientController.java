package com.clay.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope//配置刷新
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;



    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

    @GetMapping("/serverPort")
    public String getServerPort(){
        return serverPort;
    }
}
 
