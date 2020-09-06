# cloud2020
# Eureka-自我保护机制
> 保护模式主要用于一组客户端和Eureka server之间存在网络分区场景下的保护，一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务
>默认情况下EurekaClient 定时向EurekaServer端发送心跳包，如果server端在一定时间（默认90秒）没有收到EurekaClient发送心跳包，便会直接从服务器注册列表中剔除该服务，但是在短时间内（90秒中）内丢失了大量的服务器实例心跳，这时候EurekaServer会开启自我保护机制
 在cap理论里面属于 ap

使用eureka.server.enable-self-preservation = false可以禁用自我保护模式