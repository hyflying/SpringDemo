package com.example.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {
    @Test
    public void dataSourceTest() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("Spring-dataSource.xml");
        DruidDataSource dataSource = ioc.getBean(DruidDataSource.class);
        System.out.println(dataSource);
    }
}
