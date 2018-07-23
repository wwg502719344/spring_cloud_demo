package com.wwg.controller;

import com.alibaba.fastjson.JSON;
import com.wwg.emun.FilterType;
import com.wwg.entity.UserInfo;
import com.wwg.mapper.UserInfoMapper;
import com.wwg.markerMapper.MapperFilter;
import com.wwg.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by W2G on 2018/7/10.
 */
@RestController
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 测试POST类型多参数传参
     * @param
     * @return
     */
    @PostMapping("/post")
    public UserInfo post(
            @RequestBody UserInfo user
    ) {
        return user;
    }

    /**
     * 测试GET类型多参数传参
     * @param
     * @return
     */
    @GetMapping("/get")
    public UserInfo get(
            UserInfo user
    ) {
        MapperFilter mapperFilter=MapperFilter.custom(UserInfo.class)
                .addFilter("id", FilterType.EQ,user.getId())
                .addFilter("name",FilterType.EQ,user.getName())
                .build();

        List<UserInfo> u=userInfoMapper.selectByCondition(mapperFilter.getCondition());
        UserInfo users=u.get(0);
        return users;
    }

    /**
     * 普通方法测试
     * @return
     */
    @RequestMapping("/test")
    public UserInfo test() {

        MapperFilter mapperFilter=MapperFilter.custom(UserInfo.class)
                .addFilter("id", FilterType.EQ,1)
                .build();

        List<UserInfo> u=userInfoMapper.selectByCondition(mapperFilter.getCondition());
        UserInfo us=u.get(0);

        ResponseData responseData= ResponseData.getInstance();

        //直接转换陈json
        String usersJson = JSON.toJSONString(u);

        return us;
    }

    /**
     * 认证登录测试
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserInfo findById(@PathVariable Integer id) {
     /*   Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for (GrantedAuthority c : collection) {
                // 打印当前登录用户的信息
                UserController.LOGGER.info("当前用户是{}，角色是{}", user.getUsername(), c.getAuthority());
            }
        } else {
            // do other things
        }

        MapperFilter mapperFilter=MapperFilter.custom(UserInfo.class)
                .addFilter("id", FilterType.EQ,id)
                .build();

        List<UserInfo> u=userInfoMapper.selectByCondition(mapperFilter.getCondition());
        UserInfo us=u.get(0);*/
        UserInfo us=new UserInfo();
        return us;
    }



    /*@GetMapping("/get")
    public User get(User user) {
        return user;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return user;
    }*/
}
