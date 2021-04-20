package com.hbue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbue.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
