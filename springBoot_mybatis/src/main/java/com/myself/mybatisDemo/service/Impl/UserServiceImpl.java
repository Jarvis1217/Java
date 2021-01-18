package com.myself.mybatisDemo.service.Impl;

import com.myself.mybatisDemo.mapper.UserMapper;
import com.myself.mybatisDemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<String> getUserList() {
        return userMapper.getUserList();
    }
}
