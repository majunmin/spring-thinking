package com.majm.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Random;

/**
 * AspectJ 配置类 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 09:28
 * @since
 */
@Aspect
public class AspectJConfig {


    @Pointcut("execution(public * *(..))")
    public void anyMethod() { // 方法名即为 切点命名  => predicated
        System.out.println("@Pointcut any method...");
    }


    /**
     * Around 需要显示 触发  方法的执行  proceedingjoinPoint.proceed()
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("anyMethod()")
    public Object aroundExecute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around execute...");
        // before
        Object result = pjp.proceed();
        // after
        return result;
    }

    @Before("anyMethod()")
    public void beforeExecute() {
        Random ran = new Random();
//        if (ran.nextBoolean()) {
//            throw new RuntimeException("For propose!");
//        }

        System.out.println("@Before any method...");
    }


    @After("anyMethod()")
    public void finallyExecute() {
        System.out.println("@After finally execute() any method...");
    }

    @AfterThrowing("anyMethod()")
    public void afterThrowing() {
        System.out.println("@AfterThrowing  execute...");
    }

    @AfterReturning("anyMethod()")
    public void afterReturningExecute() {
        System.out.println("@AfterReturning execute...");
    }
}
