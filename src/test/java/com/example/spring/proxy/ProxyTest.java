package com.example.spring.proxy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyTest {
    @Test
    public void testProxy() {
//        CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(new CalculatorImpl());
//        calculatorStaticProxy.add(1,2);
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator propxy = (Calculator) proxyFactory.getProxy();
        propxy.add(1,2);
    }

}
