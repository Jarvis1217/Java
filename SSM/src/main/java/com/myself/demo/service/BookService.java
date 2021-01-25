package com.myself.demo.service;

import com.myself.demo.pojo.Books;

import java.util.List;

public interface BookService {
    void addBook(Books book);
    void deleteBookById(int id);
    void updateBook(Books book);
    Books queryBookById(int id);
    List<Books> queryAllBook();
}
