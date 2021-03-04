package com.myself.cashBook.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.cashBook.mapper.BillMapper;
import com.myself.cashBook.pojo.bill;
import com.myself.cashBook.service.BillService;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, bill> implements BillService {

}
