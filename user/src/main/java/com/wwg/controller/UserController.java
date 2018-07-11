package com.wwg.controller;

import com.alibaba.fastjson.JSON;

import com.wwg.emun.FilterType;
import com.wwg.entity.UserInfo;
import com.wwg.mapper.UserInfoMapper;
import com.wwg.markerMapper.MapperFilter;
import com.wwg.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by W2G on 2018/7/10.
 */
@RestController
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/test")
    public ResponseData test() {

        MapperFilter mapperFilter=MapperFilter.custom(UserInfo.class)
                .addFilter("id", FilterType.EQ,1)
                .build();

        List<UserInfo> u=userInfoMapper.selectByCondition(mapperFilter.getCondition());
        UserInfo us=u.get(0);

        ResponseData responseData= ResponseData.getInstance();
        responseData.setEntity(us);
        //直接转换陈jsonceshiwwwqweqwe
        //String usersJson = JSON.toJSONString(u);

        return responseData;
    }
}
