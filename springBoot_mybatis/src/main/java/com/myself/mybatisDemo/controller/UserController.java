package com.myself.mybatisDemo.controller;

import com.alibaba.fastjson.JSON;
import com.myself.mybatisDemo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/users")
    public String getUsers() {
        return JSON.toJSONString(userService.getUserList());
    }
}
