package com.myself.toDoList.Service;

import com.myself.toDoList.pojo.Content;
import org.springframework.data.jpa.repository.Query;

public interface ContentService {
    void addContent(Content content);
    Object getAll();
    void Delete(String text);
}
