package com.hbue.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbue.blog.mapper.UserMapper;
import com.hbue.blog.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class LogController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(@RequestParam Map<String, String> map) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uname",map.get("uname"));

        User user = userMapper.selectOne(wrapper);

        if(user == null) {
            return "用户不存在";
        }else if(!user.getUpass().equals(map.get("upass"))) {
            return "密码有误";
        }else {
            return user.getRole() == 1? "博主" : "普通用户";
        }
    }

    @RequestMapping("/register")
    public String Register(@RequestParam Map<String, String> map) {
        User user = new User();
        user.setRole(0)
                .setUname(map.get("usname"))
                .setUpass(map.get("uspass"));

        int isInsert = userMapper.insert(user);

        return "Done";
    }
}
