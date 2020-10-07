package com.clay.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class GateWayConfig {
    //路由配置方式二
    //访问 localhost:9527/guonei 路由到http://news.baidu.com/guonei
    //@Bean
    //public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
    //    RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
    //    routes.route("path_rote_atguigu", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
    //    return routes.build();
    //}
}
