package com.hbue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbue.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
