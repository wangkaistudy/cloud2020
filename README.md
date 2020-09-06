# cloud2020
# Eureka-自我保护机制
> 保护模式主要用于一组客户端和Eureka server之间存在网络分区场景下的保护，一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务
>默认情况下EurekaClient 定时向EurekaServer端发送心跳包，如果server端在一定时间（默认90秒）没有收到EurekaClient发送心跳包，便会直接从服务器注册列表中剔除该服务，但是在短时间内（90秒中）内丢失了大量的服务器实例心跳，这时候EurekaServer会开启自我保护机制
 在cap理论里面属于 ap

使用eureka.server.enable-self-preservation = false可以禁用自我保护模式


# zookeeper 作为注册中心
> zookeeper是一个分布式协调工具，可以实现注册中心功能，保证ap，缺点是没有图形化管理界面

#  consul
> consule 是一套开源的分布式服务发现和配置管理系统,由HashiCorp公司用Go语言开发
> 提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中每一个都可以根据需要
>单独使用，也可以一起使用构建全方位的服务网格

**优点** ：基于raft协议，简洁、支持健康检查、支持https和dns协议，支持跨数据中心平台，支持linux、mac、windows、支持可视化web界面

下载地址：https://www.consul.io/downloads.htm
相关教程：https://www.springcloud.cc/spring-cloud-consul.html