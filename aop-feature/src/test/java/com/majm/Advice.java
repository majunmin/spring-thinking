package com.majm;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-03 07:59
 * @since
 */
@Aspect
public class Advice {

    @Pointcut("execution(* com.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(){
        System.out.println("do before");
    }
}
