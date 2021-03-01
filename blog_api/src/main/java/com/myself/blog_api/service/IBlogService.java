package com.myself.blog_api.service;

import com.myself.blog_api.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface IBlogService {
    //查询所有博客
    List<Blog> queryAllBlog();
    //模糊查询
    Blog queryBlogByName(String bTitle);
    //分页查询
    List<Map<String, Object>> selectBlogPageWhere(Map<String, Object> map);
    //发布博客
    int addBlog(Blog blog);
    //删除博客
    int deleteBlogByName(String bTitle);
    //微博总数
    int queryAllNum();
    //test
    Blog queryBlogById(int bId);
}
