package com.myself.blog_api.mapper;

import com.myself.blog_api.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    //查询所有博客
    List<Blog> queryAllBlog();
    //模糊查询
    Blog queryBlogByName(String bTitle);
    //分页查询
    List<Map<String, Object>> selectBlogPageWhere(Map<String, Object> map);
    //增加博客
    int addBlog(Blog blog);
    //博客总数
    int queryAllNum();
    //删除博客
    int deleteBlogByName(String bTitle);
    //test
    Blog queryBlogById(int bId);
}
