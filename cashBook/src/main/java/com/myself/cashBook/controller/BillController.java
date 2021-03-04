package com.myself.cashBook.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.cashBook.pojo.bill;
import com.myself.cashBook.service.Impl.BillServiceImpl;
import com.myself.cashBook.utils.DateUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
public class BillController {

    @Resource
    private BillServiceImpl billService;

    @RequestMapping("/insert")
    public String insert(@RequestParam Map<String, String> map) {
        bill bill = new bill();
        bill.setType(map.get("type"));
        bill.setPrice(Double.parseDouble(map.get("price")));
        bill.setDate(DateUtil.date2TimeStamp(map.get("date"),"yyyy-MM-dd"));
        bill.setMark(map.get("explain"));

        billService.save(bill);
        return "success";
    }

    @RequestMapping("/allBill")
    public String allBill() {
        Page<bill> page = new Page<>(1, 5);
        return JSON.toJSONString(billService.page(page, null).getRecords());
    }

    @RequestMapping("/search")
    public String search(@RequestParam Map<String, String> map) {
        Page<bill> page = new Page<>(1, 5);
        QueryWrapper<bill> wrapper = new QueryWrapper<>();

        if (!map.get("scType").equals("不限")) {
            wrapper.eq("type", map.get("scType"));
        }
        if (!map.get("timeBegin").equals("")) {
            wrapper.ge("date", DateUtil.date2TimeStamp(map.get("timeBegin"),"yyyy-MM-dd"));
        }
        if (!map.get("timeEnded").equals("")) {
            wrapper.le("date", DateUtil.date2TimeStamp(map.get("timeEnded"),"yyyy-MM-dd"));
        }
        return JSON.toJSONString(billService.page(page,wrapper).getRecords());
    }
}
