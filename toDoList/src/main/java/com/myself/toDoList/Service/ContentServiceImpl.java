package com.myself.toDoList.Service;

import com.myself.toDoList.Dao.ContentDao;
import com.myself.toDoList.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Override
    public void addContent(Content content) {
        contentDao.save(content);
    }

    @Override
    public Object getAll() {
        return contentDao.findAll();
    }

    @Override
    public void Delete(String text) {
        contentDao.DeleteByText(text);
    }
}
