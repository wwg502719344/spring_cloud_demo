package com.w2g.controller;

import com.w2g.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  private static Logger log = LoggerFactory.getLogger(UserInfo.class);

  @GetMapping("/user/{id}")
  public UserInfo findById(@PathVariable int id) {
    return this.restTemplate.getForObject("http://user-ribbon/test", UserInfo.class);
  }

  @GetMapping("/log-instance")
  public void showinstance() {
    //获取进行负载均衡器中user_ribbon注册器下的实例
    //后期可改造为官网推荐的@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)的形式
    ServiceInstance serviceInstance=this.loadBalancerClient.choose("user-ribbon");

    MovieController.log.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());

  }

}
