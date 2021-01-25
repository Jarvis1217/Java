package com.myself.ssm.service.Impl;

import com.myself.ssm.mapper.BaseMapper;
import com.myself.ssm.service.BaseService;

public class BaseServiceImpl implements BaseService {
    private BaseMapper baseMapper;

    public void setBaseMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }
}
