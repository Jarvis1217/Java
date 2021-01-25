package com.myself.demo.controller;

import com.myself.demo.pojo.Books;
import com.myself.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/test",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String test() {
        return "TestPage,你好世界";
    }
}
