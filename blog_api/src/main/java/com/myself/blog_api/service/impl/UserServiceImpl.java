package com.myself.blog_api.service.impl;

import com.myself.blog_api.mapper.UserMapper;
import com.myself.blog_api.pojo.User;
import com.myself.blog_api.service.IUserService;

public class UserServiceImpl implements IUserService {
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Boolean userLogin(String token) {
        return userMapper.userLogin(token);
    }

    public String queryToken() {
        return userMapper.queryToken();
    }

    public int updateToken(String token) {
        return userMapper.updateToken(token);
    }

    public int userRegister(User user) {
        return userMapper.userRegister(user);
    }
}
