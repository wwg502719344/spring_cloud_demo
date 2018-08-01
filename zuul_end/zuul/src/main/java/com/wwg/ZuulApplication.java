package com.wwg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

	/**
	 *	EnableZuulProxy申明一个zuul代理，该代理使用了ribbon来定位注册在eureka上的微服务，
	 * 	而且整合了hstrix，所以实现了容错
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

}
