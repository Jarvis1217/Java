package com.myself.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.blog.mapper.PassKeyMapper;
import com.myself.blog.pojo.PassKey;
import com.myself.blog.service.PassKeyService;
import org.springframework.stereotype.Service;

@Service
public class PassKeyServiceImpl extends ServiceImpl<PassKeyMapper,PassKey> implements PassKeyService {

}
