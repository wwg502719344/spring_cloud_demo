package com.wwg.mapperXml;


import com.wwg.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by W2G on 2018/7/10.
 */
@Mapper
public interface UserDao {
    List<UserInfo> searchAll();

}
