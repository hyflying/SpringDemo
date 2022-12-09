package com.example.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggerAspect {
    @Pointcut("execution(* com.example.spring.aop.annotation.CalculatorImpl.*(..))")
    public void pointCut() {

    }
//    @Before("execution(public int com.example.spring.aop.annotation.CalculatorImpl.add(int, int))")
//    @Before("execution(* com.example.spring.aop.annotation.CalculatorImpl.*(..))")
    @Before("pointCut()")
    public void beforeAdviceMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法名："+signature+",参数："+ Arrays.toString(args));
        System.out.println("Logger Aspect：前置通知");
    }
    @After("pointCut()")
    public void aferAdviceMethod() {
        System.out.println("执行结束");
    }

    @Around("pointCut()")
    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("环绕通知：前置通知");
            result = joinPoint.proceed();
            System.out.println("环绕通知：返回通知");
        } catch (Throwable throwable){
            throwable.printStackTrace();
            System.out.println("环绕通知：异常通知");
        } finally {
            System.out.println("环绕通知：后置通知");
        }
        return result;
    }
}
