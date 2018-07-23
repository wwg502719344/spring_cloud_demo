package com.w2g.controller;

import com.w2g.entity.UserInfo;
import com.w2g.interfaces.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//spring cloud 为feign提供的默认配置类
//@Import(FeignClientsConfiguration.class)
//@RibbonClient(name = "user-ribbon", configuration = RibbonConfiguration.class)    //可以改为yml配置
@RestController
public class MovieController {
  /*@Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @Autowired
  private UserFeignClient userFeignClient;*/
  @Autowired
  private UserFeignClient userUserFeignClient;

  private static Logger log = LoggerFactory.getLogger(UserInfo.class);

  /**
   * 测试URL：http://localhost:8200/user/post?id=1&name=W2G
   * POST类型多参数传参
   * @param user user
   * @return 用户信息
   */
  @PostMapping("/user/post")
  public UserInfo post(@RequestBody UserInfo user) {
    return this.userUserFeignClient.post(user);
  }


  /**
   * 测试URL：http://localhost:8200/user/get1?id=1&name=W2G
   * GET类型多参数传参
   * @param
   * @return 用户信息
   */
  /*@GetMapping("/user/get1")
  public UserInfo get(UserInfo user) {
    return this.userUserFeignClient.get1(user.getId(), user.getName());
  }*/

  @GetMapping("/user-user/{id}")
  public UserInfo findById1(@PathVariable int id) {
    //return this.restTemplate.getForObject("http://user-ribbon/test", UserInfo.class);
    //调用userFeignClient配置传参
    return this.userUserFeignClient.findById(id);
  }

  /**
   * 手动创建feign clients
   */
  /*@Autowired
  public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
    this.userUserFeignClient = Feign.builder().client(client)
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
            .target(UserFeignClient.class, "http://user-feign");
  }*/

  /*@GetMapping("/user/{id}")
  public UserInfo findById(@PathVariable int id) {
    //return this.restTemplate.getForObject("http://user-ribbon/test", UserInfo.class);
    //调用userFeignClient配置传参
    return this.userFeignClient.findById(id);
  }

  @GetMapping("/log-instance")
  public void showinstance() {
    //获取进行负载均衡器中user_ribbon注册器下的实例
    //后期可改造为官网推荐的@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)的形式
    ServiceInstance serviceInstance=this.loadBalancerClient.choose("user-ribbon");

    MovieController.log.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());

  }*/




}
