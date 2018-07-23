/*


import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
package com.w2g.config;
import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by W2G on 2018/7/17.
 */
//如果使用自定义feign配置，需要注释掉该类
/*@Configuration
public class FeignConfiguration {


    *//**
     * Creating a bean of one of those type and placing it in a @FeignClient configuration
     * (such as FooConfiguration above) allows you to override each one of the beans described.
     * 创建一个configuration类去覆盖默认的feign配置
     *//*
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }


}*/

