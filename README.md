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

# Ribbon
> Ribbon是netfilx 发布的开源项目，主要是提供客户端的软件负载均衡算法和服务调用，Ribbon客户端组件组件是提供一系列完善的配置如:链接超时、重试、负载均衡等
>目前进入维护模式，未来替换spring cloud loand balance
>
>总结：Ribbon其实就是一个软负载均衡的客户端组件，他可以和其他所需请求的客户端结合使用，和eureka结合只是其中的一个实例。
>eureka 默认已经引入Ribbon
>
## 自定义负载均衡
> IRule:根据特定算法从服务列表中选取一个要访问的服务

* 轮询: com.netflix.loadbalancer.RoundRobinRule
* 随机: com.netflix.loadbalancer.RandomRule
* com.netflix.loadbalancer.RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试
* WeightedResponseTimeRule  对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
*BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
* AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
* ZoneAvoidanceRule 默认规则，复合判断server所在区域的性能和server的可用性选择服务器
注意： 自定义配置类不能放在@componentScan所扫描的当前包下以及子包下，否则我们自定义的配置类会被所有的Ribbon客户端锁共享，达不到特殊化制定的目的

创建自定义的类，再启动类增加注解@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)

# Fegin
> Feign是一个声明式的web服务客户端，让编写web服务客户端变得非常容易，只需创建一个接口并在接口上添加注解即可
> Feign 支持可插拔式的编码器和解码器，spring Cloud对feign进行了封装，使其支持了spring mvc标准注解和HttpMessageConverters.Fein可以与Eueka和Ribbon组合使用以支持负载均衡
>Feign集成了Ribbon 与Ribbon不同的是，通过Feign只需要定义服务绑定接口以声明式的方法，优雅的实现了服务调用**
## Fegin 与Open Fein的区别
feign ：是springcloud组件中的一个轻量级的restful的http服务客户端Feign内置了Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。 feign使用方式是：使用feign的注解定义接口，调用这个接口，就可以调用服务注册中心
OpenFein： 是springcloud在feign的基础上支持了springMvc的注解如@requestmapping等，OpenFeign的@FeginClient可以解析 springMVc的@requestMapping注解下的接口，并通过动态代理的方式产生实现类，实现类中做负载均衡调用其他服务
## openFeign超时控制
OpenFeign默认等待一秒钟，超过后报错,Feign整合了ribbon 所以只需配置
ribbon:
  ReadTimeout:  5000
  ConnectTimeout: 5000
 即可
 ## openFeign日志打印
 openfeign提供了日志打印功能。
 Logger有四种类型：NONE(默认，不显示任何日志)、BASIC(记录请求方法、URL、响应状态码、执行时间)、
 HEADERS(除了basic还有请求和响应头信息)、FULL(除header外还有请求和响应正文及元数据)，通过注册Bean来设置日志记录级别
 
 # Hystrix
 ## 介绍
 > 在微服务场景中，通常会有很多层的服务调用。如果一个底层服务出现问题，故障会被向上传播给用户。我们需要一种机制，当底层服务不可用时，可以阻断故障的传播。这就是断路器的作用。他是系统服务稳定性的最后一重保障。
   
 >  在springcloud中断路器组件就是Hystrix。Hystrix也是Netflix套件的一部分。他的功能是，当对某个服务的调用在一定的时间内（默认10s），有超过一定次数（默认20次）并且失败率超过一定值（默认50%），该服务的断路器会打开。返回一个由开发者设定的fallback。
   
 > fallback可以是另一个由Hystrix保护的服务调用，也可以是固定的值。fallback也可以设计成链式调用，先执行某些逻辑，再返回fallback。

## Hystrix的作用

1. 对通过第三方客户端库访问的依赖项（通常是通过网络）的延迟和故障进行保护和控制。

2. 在复杂的分布式系统中阻止级联故障。

3. 快速失败，快速恢复。

4. 回退，尽可能优雅地降级。

5. 启用近实时监控、警报和操作控制。

## Hystirx重要概念
###  服务降级
> 服务器忙，请稍候再试，不让客户端等待并立刻返回一个友好提示，fallback
**触发条件** ：程序运行异常、超时、服务熔断触发服务降级、线程池/信号量打满也会导致服务降级#
       
### 服务熔断
> 类比保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法并返回友好提示

### 服务限流
> 秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行