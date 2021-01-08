package com.myself.servwork.Dao;

import java.util.List;

public interface shoppingDao {
    List<String> findAll();
    void deleteByText(String text);
    void addContent(String text);
}
