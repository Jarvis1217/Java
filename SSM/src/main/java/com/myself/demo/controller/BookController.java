package com.myself.demo.controller;

import com.alibaba.fastjson.JSON;
import com.myself.demo.pojo.Books;
import com.myself.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allbook")
    public String list(Model model) {
        List<Books> books = bookService.queryAllBook();
        model.addAttribute("books",books);
        return "allbook";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("test1","测试文本");
        map.put("test2","测试数据");
        map.put("test3","测试内容");
        return JSON.toJSONString(map);
    }
}
