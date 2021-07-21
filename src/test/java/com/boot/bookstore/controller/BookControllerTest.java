package com.boot.bookstore.controller;

import com.boot.bookstore.entity.Book;
import com.boot.bookstore.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Test
    void findAll() {
//        System.out.println(bookController.findAll());
    }

    @Test
    void findById(Integer id) {
        System.out.println(bookController.findById(id));
    }

    @Test
    void insert(Book book) {
//        System.out.println(bookController.insert(book));
    }

    @Test
    void update(Book book) {
        System.out.println(bookController.update(book));
    }

    @Test
    void delete(Integer id) {
        bookController.delete(id);
    }
}