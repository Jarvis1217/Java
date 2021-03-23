package com.myself.blog.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.blog.pojo.Blog;
import com.myself.blog.service.impl.BlogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/blog")
@Api(tags = "博客文章控制层")
public class BlogController {
    @Resource
    private BlogServiceImpl blogService;


    @ApiOperation("获得指定页的数据")
    @ApiResponse(code = 200,
            message = "返回json对象，包含全部数据data 当前页码curPage 总页数allPage,例如" +
                    "{ curPage:当前页数, data:[], allPage:总页数 }")
    @GetMapping("/all/{leaf}")
    public String getAll(@PathVariable int leaf) {
        Page<Blog> page = new Page<>(leaf, 6);

        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("postTime");

        return getString(page, wrapper);
    }

    @ApiOperation("获取博客总篇数")
    @GetMapping("/count")
    public int getCount() {
        return blogService.count();
    }

    @ApiOperation(value = "保存新提交的博客",
            notes = "接收参数为map,键分别为title,intro,content")
    @PostMapping("/addBlog")
    public String addBlog(@RequestBody Map<String, String> map) {
        Blog blog = new Blog();
        blog.setTitle(map.get("title"));
        blog.setIntro(map.get("intro"));
        blog.setContent(map.get("content"));
        blog.setPostTime(new Date());

        boolean isSave = blogService.save(blog);
        return isSave? "save success" : "failed to save";
    }

    @ApiOperation(value = "根据标题删除博客",
            notes = "接收参数为map,对应的键是title")
    @PostMapping("/delBlog")
    public String delBlog(@RequestBody Map<String,String> map) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("title",map.get("title"));

        boolean isRemove = blogService.remove(wrapper);
        return isRemove? "delete success" : "failed to delete";
    }

    @ApiOperation("获得模糊查询且分页后的结果")
    @ApiResponse(code = 200,
            message = "返回json对象，包含全部数据data 当前页码curPage 总页数allPage,例如" +
                    "{ curPage:当前页数, data:[], allPage:总页数 }")
    @GetMapping("/search/{leaf}/{word}")
    public String SearchBlog(@PathVariable int leaf,@PathVariable String word) {
        Page<Blog> page = new Page<>(leaf, 6);

        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("postTime")
                .like("lower(title)","lower("+word+")");

        return getString(page, wrapper);
    }


    /**
     * 分页查询全部数据或是分页模糊查询全部数据时的公共方法
     * */
    private String getString(Page<Blog> page, QueryWrapper<Blog> wrapper) {
        Page<Blog> blogPage = blogService.page(page, wrapper);

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", blogPage.getRecords());
        result.put("curPage", blogPage.getCurrent());
        result.put("allPage", blogPage.getPages());

        return JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss");
    }
}
