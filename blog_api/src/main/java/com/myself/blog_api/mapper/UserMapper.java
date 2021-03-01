package com.myself.blog_api.mapper;

import com.myself.blog_api.pojo.User;

public interface UserMapper{
    //登录
    Boolean userLogin(String token);
    //获取token
    String queryToken();
    //修改token
    int updateToken(String token);
    //注册
    int userRegister(User user);
}
