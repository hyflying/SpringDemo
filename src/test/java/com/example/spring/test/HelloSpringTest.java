package com.example.spring.test;

import com.example.spring.pojo.HelloSpring;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringTest {
    @Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloSpring spring = (HelloSpring) ioc.getBean("helloWorld");
        spring.sayHello();
    }
}
