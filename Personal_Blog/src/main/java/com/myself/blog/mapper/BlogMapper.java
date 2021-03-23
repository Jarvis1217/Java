package com.myself.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myself.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
