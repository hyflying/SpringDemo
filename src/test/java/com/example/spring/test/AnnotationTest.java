package com.example.spring.test;

import com.example.spring.controller.UserController;
import com.example.spring.dao.UserDao;
import com.example.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    @Test
    public void testAnnotation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-annotation.xml");
//        UserController userController = ioc.getBean(UserController.class);
//        System.out.println(userController);
//        UserDao userDao = ioc.getBean(UserDao.class);
//        System.out.println(userDao);
//        UserService userService = ioc.getBean(UserService.class);
//        System.out.println(userService);
        UserController userController = ioc.getBean("controller", UserController.class);
        userController.saveUser();
    }
}
