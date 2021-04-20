package com.hbue.blog.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbue.blog.mapper.CommentMapper;
import com.hbue.blog.pojo.Comment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class CommentController {
    @Resource
    private CommentMapper commentMapper;

    @RequestMapping("/postComment")
    public String postComment(@RequestParam Map<String,String> map) {
        Comment comment = new Comment();
        comment.setUname(map.get("uname"))
                .setContent(map.get("content"))
                .setPostTime(new Date())
                .setLng(Double.parseDouble(map.get("lng")))
                .setLat(Double.parseDouble(map.get("lat")));

        commentMapper.insert(comment);
        return "done";
    }

    @RequestMapping("/getComments")
    public String getComments(@RequestParam Map<String,String> map) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("lng",Double.parseDouble(map.get("lng")))
                .eq("lat",Double.parseDouble(map.get("lat")));

        List<Comment> comments = commentMapper.selectList(wrapper);

        return JSON.toJSONStringWithDateFormat(comments,"yyyy-MM-dd HH:mm:ss");
    }
}
