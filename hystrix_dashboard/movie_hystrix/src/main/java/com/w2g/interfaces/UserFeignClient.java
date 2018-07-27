package com.w2g.interfaces;

import com.w2g.entity.UserInfo;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by W2G on 2018/7/17.
 * Feign fallback测试
 * 使用feignClient的fallback属性指定的回退类
 */
@FeignClient(name="user-hystrix",fallbackFactory=FeignClientFallback.class)
public interface UserFeignClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserInfo findById(@PathVariable("id") int id );

}

@Component
class FeignClientFallback implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient(){

            @Override
            public UserInfo findById(int id) {
                UserInfo userInfo=new UserInfo();
                userInfo.setId(-1);
                userInfo.setName("默认用户");

                return userInfo;
            }
        };
    }
}