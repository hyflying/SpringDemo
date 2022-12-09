package com.example.spring.test;

import com.example.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {
    @Test
    public void testScope() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-scope.xml");
        Student s1 = ioc.getBean(Student.class);
        Student s2 = ioc.getBean(Student.class);
        System.out.println(s1 == s2);
    }
}
