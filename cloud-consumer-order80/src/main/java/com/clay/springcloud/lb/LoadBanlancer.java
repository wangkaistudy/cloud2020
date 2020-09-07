package com.clay.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBanlancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
