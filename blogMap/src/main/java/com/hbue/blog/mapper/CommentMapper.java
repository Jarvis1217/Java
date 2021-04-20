package com.hbue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbue.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
