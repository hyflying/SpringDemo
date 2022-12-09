package com.example.spring.test;

import com.example.spring.pojo.Department;
import com.example.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentByXMLTest {
    @Test
    public void testIOC(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //用id获取类
//        Student studentOne = (Student) ioc.getBean("studentOne");

        //用class获取类
//        Student studentOne = ioc.getBean(Student.class);
//        System.out.println(studentOne.toString());
        Student studentOne = ioc.getBean("studentOne",Student.class);
        System.out.println(studentOne.toString());
    }

    @Test
    public void testDI(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student studentOne = ioc.getBean("studentTwo",Student.class);
        System.out.println(studentOne.toString());
    }

    @Test
    public void testStudentThree(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student studentOne = ioc.getBean("studentThree",Student.class);
        System.out.println(studentOne.toString());
    }
    @Test
    public void testStudentFour(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student studentOne = ioc.getBean("studentFour",Student.class);
        System.out.println(studentOne.toString());
    }

    @Test
    public void testStudentFive(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student studentOne = ioc.getBean("studentFive",Student.class);
        System.out.println(studentOne.toString());
    }

    @Test
    public void testDepartment(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Department department = ioc.getBean("CS", Department.class);
        System.out.println(department.toString());
    }
}
