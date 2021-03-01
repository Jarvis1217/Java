package com.myself.blog_api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.myself.blog_api.mapper.BlogMapper;
import com.myself.blog_api.pojo.Blog;
import com.myself.blog_api.service.IBlogService;

import java.util.List;
import java.util.Map;

public class BlogServiceImpl implements IBlogService {
    private BlogMapper blogMapper;

    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    //查询所有博客
    public List<Blog> queryAllBlog() {
        return blogMapper.queryAllBlog();
    }
    //模糊查询
    public Blog queryBlogByName(String bTitle){
        return blogMapper.queryBlogByName(bTitle);
    }
    //分页查询
    public List<Map<String, Object>> selectBlogPageWhere(Map<String, Object> map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum")+""), 8, " bId desc");
        return blogMapper.selectBlogPageWhere(map);
    }

    //发布博客
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    //删除博客
    public int deleteBlogByName(String bTitle) {
        return blogMapper.deleteBlogByName(bTitle);
    }

    //微博总数
    public int queryAllNum(){
        return blogMapper.queryAllNum();
    }
    //test
    public Blog queryBlogById(int bId) {
        return blogMapper.queryBlogById(bId);
    }
}
