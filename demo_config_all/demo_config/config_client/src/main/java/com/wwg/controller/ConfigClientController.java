package com.wwg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by W2G on 2018/8/3.
 *
 */
@RestController
public class ConfigClientController {

    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String hello(){
        return this.profile;
    }
}
