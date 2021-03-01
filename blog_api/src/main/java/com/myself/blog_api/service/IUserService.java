package com.myself.blog_api.service;

import com.myself.blog_api.pojo.User;

public interface IUserService {
    //登录
    Boolean userLogin(String token);
    //查询token
    String queryToken();
    //修改token
    int updateToken(String token);
    //注册
    int userRegister(User user);
}