package com.example.spring.bookDemo.controller;

import com.example.spring.bookDemo.service.BookService;
import com.example.spring.bookDemo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CheckoutService checkoutService;

    public void buyBook(Integer bookId, Integer userId) {
        bookService.buyBook(bookId,userId);
    }

    @Transactional
    public void checkout(Integer userId, Integer[] books) {
       checkoutService.checkout(userId, books);
    }
}
