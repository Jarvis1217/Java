package com.myself.toDoList.Controller;

import com.myself.toDoList.Service.ContentService;
import com.myself.toDoList.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/")
@RestController
@CrossOrigin
public class baseController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("save")
    public String saveText(@RequestBody Map<String, String> map) {
        Content content = new Content();
        content.setText(map.get("text"));

        contentService.addContent(content);
        return "success";
    }

    @RequestMapping("query")
    public Object getAll() {
        return contentService.getAll();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String, String> map) {
        contentService.Delete(map.get("text"));
        return "success";
    }
}