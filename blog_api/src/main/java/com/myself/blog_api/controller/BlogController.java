package com.myself.blog_api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.myself.blog_api.pojo.Blog;
import com.myself.blog_api.service.IBlogService;
import com.myself.blog_api.util.ReturnData;
import com.myself.blog_api.util.StatusCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    @Qualifier("blogServiceImpl")
    private IBlogService blogService;

//    //查询所有博客
//    @RequestMapping("/queryAll")
//    public void/*Object*/ queryAllBlog(){
//        List<Blog> list = blogService.queryAllBlog();
//        System.out.println(list);
////        return JSONObject.toJSON(new ReturnData(StatusCode.REQUEST_SUCCESS, blogService.queryAllBlog()));
//    }
//
    //博客搜索
    @RequestMapping("/queryBlogByName")
    public String queryBlogByName(@Param("title") String title){
        return JSON.toJSONString(blogService.queryBlogByName(title));
    }

    //pageHelper分页
    @RequestMapping("/page")
    public String selectBlogPageHelper(@RequestParam Map<String, Object> map){
        List<Map<String, Object>> list = blogService.selectBlogPageWhere(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        map.put("list", list);
        map.put("total", pageInfo.getTotal());
        return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss");
    }

    //发布博客
    @RequestMapping("/addBlog")
    public void addBlog(@RequestParam Map<String, Object> map){
//        System.out.println(map.get("content"));
        Blog blog = new Blog();
        blog.setBTitle(map.get("title").toString());
        blog.setBContent(map.get("content").toString());
        blog.setBDate(new Date());
        blogService.addBlog(blog);
    }

    //删除博客
    @RequestMapping("/delete")
    public void deleteBlog(@Param("title") String title){
        blogService.deleteBlogByName(title);
    }

    @RequestMapping("/count")
    public String queryAllNum(){
        return JSON.toJSONString(blogService.queryAllNum());
    }

    @RequestMapping("/test")
    public void Test(){
//        Blog blog = blogService.queryBlogById(1);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(blog.getBDate());
//        int month = cal.get(Calendar.DATE);
//        System.out.println(month);
//        System.out.println(blog.getBDate().getDate());
//        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
//        System.out.println(sdf.format(blog.getBDate()));
    }
}