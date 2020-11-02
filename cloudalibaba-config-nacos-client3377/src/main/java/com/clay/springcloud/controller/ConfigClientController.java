package com.clay.springcloud.controller;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.Executor;

@RestController
@RefreshScope//实现配置自动更新
public class ConfigClientController {
    @Value("${config.info:123}")
    private String configInfo;

    @Autowired
    private NacosConfigProperties configProperties;


    @GetMapping("/config/info")
    public String getConfigInfo() throws NacosException, IOException {

        ConfigService configService = NacosFactory.createConfigService(configProperties.getServerAddr());
        String config = configService.getConfig("nacos-config-client-test.yml", configProperties.getGroup(), 5000);
        System.out.println("first config："+config);
        configService.addListener("nacos-config-client-test.yml", configProperties.getGroup(), new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String s) {
                System.out.println("间听到消息："+s);
            }
        });


        return configInfo;
    }
}
