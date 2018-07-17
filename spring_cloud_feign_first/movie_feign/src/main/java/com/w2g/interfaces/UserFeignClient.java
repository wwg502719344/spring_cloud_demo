package com.w2g.interfaces;

import com.w2g.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by W2G on 2018/7/17.
 */
//此处可以指定url地址@FeignClient(name="user-feign",url="http:localhost:8088/test")
@FeignClient(name="user-feign")
public interface UserFeignClient {

    @GetMapping(value="/test")
    public UserInfo findById(@PathVariable("id") int id );
}
