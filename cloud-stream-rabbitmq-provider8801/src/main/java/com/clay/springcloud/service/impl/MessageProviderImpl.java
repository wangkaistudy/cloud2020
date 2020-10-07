package com.clay.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.clay.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

//定义消息得推送官道
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;


    @Override
    public String send() {

        String uuid = UUID.fastUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("----------发送消息:{}--------",uuid);

        return null;
    }
}
