package com.example.spring.bookDemo.service.impl;

import com.example.spring.bookDemo.dao.BookDao;
import com.example.spring.bookDemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED
    )
    public void buyBook(Integer userId, Integer bookId) {
        Integer price = bookDao.getPriceByBookId(bookId);
        bookDao.updateStock(bookId);
        bookDao.updateBalance(userId, price);
    }

}
