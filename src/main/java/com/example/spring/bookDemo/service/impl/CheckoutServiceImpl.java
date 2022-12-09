package com.example.spring.bookDemo.service.impl;

import com.example.spring.bookDemo.service.BookService;
import com.example.spring.bookDemo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private BookService bookService;

    @Override
    @Transactional
    public void checkout(Integer userId, Integer[] books) {
        for(Integer bookId: books) {
            bookService.buyBook(userId, bookId);
        }
    }
}
