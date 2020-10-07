package com.clay.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;


@SpringBootApplication

public class CloudStreamConsumerMain8802 {

    //@StreamListener(Sink.INPUT)
    //public void input(Message<String> message) {
    //    System.out.println("消费者1号，接受："+message.getPayload()+"\t port:");
    //}

    public static void main(String[] args) {
        SpringApplication.run(CloudStreamConsumerMain8802.class, args);
    }
}
