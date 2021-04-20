package com.hbue.blog.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbue.blog.mapper.BlogMapper;
import com.hbue.blog.pojo.Blog;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BlogController {
    @Resource
    private BlogMapper blogMapper;

    @RequestMapping("/postBlog")
    public String postBlog(@RequestParam Map<String,String> map) {
        Blog blog = new Blog();
        blog.setContent(map.get("content"))
                .setLng(Double.parseDouble(map.get("lng")))
                .setLat(Double.parseDouble(map.get("lat")))
                .setPostTime(new Date());

        blogMapper.insert(blog);

        return "done";
    }

    @RequestMapping("/getAllPoint")
    public String getAllPoint() {
        List<Blog> blogs = blogMapper.selectList(null);
        List<List<Double>> points = new ArrayList<>();

        for(Blog blog : blogs) {
            ArrayList<Double> list = new ArrayList<>();

            list.add(blog.getLng());
            list.add(blog.getLat());

            points.add(list);
        }

        return JSON.toJSONString(points);
    }

    @RequestMapping("/getContent")
    public String getContent(@RequestParam Map<String,Double> map) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("lng",map.get("lng"))
                .eq("lat",map.get("lat"));

        List<Blog> blogs = blogMapper.selectList(wrapper);

        return JSON.toJSONStringWithDateFormat(blogs,"yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/delBlog")
    public String delBlog(@RequestParam Map<String,Double> map) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("lng",map.get("lng"))
                .eq("lat",map.get("lat"));

        blogMapper.delete(wrapper);

        return "done";
    }
}
