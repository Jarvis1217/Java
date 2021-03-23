package com.myself.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myself.blog.pojo.PassKey;
import com.myself.blog.service.impl.PassKeyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/blogger")
@Api(tags = "通行口令控制层")
public class PassKeyController {

    @Resource
    private PassKeyServiceImpl passKeyService;

    @ApiOperation("判断登录口令是否有误")
    @GetMapping("/login/{passKey}")
    public String login(@PathVariable String passKey) {
        QueryWrapper<PassKey> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passKey", passKey);

        if (passKeyService.getOne(queryWrapper) != null) {
            return "pass";
        } else {
            return "no pass";
        }
    }

    @ApiOperation(value = "修改登录口令", notes = "接收参数为map," +
            "接收格式为{ \"passKey\": \"修改后的口令\" }")
    @PostMapping("/updateKey")
    public String updateKey(@RequestBody Map<String, String> map) {
        boolean update = passKeyService.updateById(new PassKey(1, map.get("passKey")));

        if (update) {
            return "update success";
        } else {
            return "failed to update";
        }
    }

    @ApiOperation(value = "获取登录口令", notes = "接收参数为map,作为接口保护，" +
            "传递固定的密钥，接收数据为{ \"secret\": \"For Protect Api\" }")
    @PostMapping("/getKey")
    public String getKey(@RequestBody Map<String, String> map) {
        if (map.get("secret").equals("For Protect Api")) {
            PassKey key = passKeyService.getById(1);
            return key.getPassKey();
        } else {
            return "security key is required";
        }
    }
}
