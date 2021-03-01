package com.myself.blog_api.controller;

import com.alibaba.fastjson.*;
import com.myself.blog_api.pojo.User;
import com.myself.blog_api.service.IUserService;
import com.myself.blog_api.service.impl.UserServiceImpl;
import com.myself.blog_api.util.ReturnData;
import com.myself.blog_api.util.StatusCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService iUserService;

    //登录
    @RequestMapping("/login")
    public String Login(@Param("token") String token){
        boolean bool;
        if(iUserService.userLogin(token) == null){
            bool = false;
        } else {
            bool = true;
        }
/*        if(iUserService.userLogin(token) != 0){
            System.out.println(1);
            return "true";
        } else {
            System.out.println(2);
            return "false";
        }*/
//        if (iUserService.userLogin(token))
        return bool ? "true" : "false";
    }

    //获取token
    @RequestMapping("/queryToken")
    public String queryToken(){
        return iUserService.queryToken();
    }

    //修改token
    @RequestMapping("/updateToken")
    public String updateToken(@Param("token") String token){
        boolean bool;
        bool = iUserService.updateToken(token) != 0;
        return bool ? "true" : "false";
    }
//    @RequestMapping("/register")
//    public void/*Object*/ Register(@Param("uName") String uName, @Param("uPassword") String uPassword){
//        User user = new User();
//
//        if(iUserService.userRegister(user) != 0){
//            System.out.println(1);
////            return JSONObject.toJSON(new ReturnData(StatusCode.REQUEST_SUCCESS, "注册成功"));
//        } else {
//            System.out.println(2);
////            return JSONObject.toJSON(new ReturnData(StatusCode.REQUEST_FALSE, "注册成功"));
//        }
//    }
//
//    @RequestMapping("/test")
//    public String test(){
//        return "123";
//    }
}