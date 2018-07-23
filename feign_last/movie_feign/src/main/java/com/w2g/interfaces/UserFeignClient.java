package com.w2g.interfaces;

import com.w2g.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by W2G on 2018/7/17.
 * 用于使用者的调用
 */
@FeignClient(name = "user-feign")
public interface UserFeignClient {

    /**
     * RequestLine:自定义访问方式
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserInfo findById(@PathVariable("id") int id);

    /*@RequestMapping(value = "/get", method = RequestMethod.GET)
    public UserInfo get1(@RequestParam("id") int id, @RequestParam("name") String name);*/

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public UserInfo post(@RequestBody UserInfo userInfo);
}
