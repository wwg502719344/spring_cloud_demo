package com.wwg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by W2G on 2018/8/3.
 *
 */
@RestController
@RefreshScope   //在配置更改时得到特殊的处理
public class ConfigClientController {

    @Value("${profile}")  //${profile}需要准确读取到配置文件中的数据，否则打包会报错
    private String profile;

    @GetMapping("/profile")
    public String hello(){
        return this.profile;
    }
}
