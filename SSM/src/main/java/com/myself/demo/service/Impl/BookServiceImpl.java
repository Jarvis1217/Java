package com.myself.demo.service.Impl;

import com.myself.demo.mapper.BookMapper;
import com.myself.demo.pojo.Books;
import com.myself.demo.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public void addBook(Books book) {
        bookMapper.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookMapper.deleteBookById(id);
    }

    @Override
    public void updateBook(Books book) {
        bookMapper.updateBook(book);
    }

    @Override
    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }
}
