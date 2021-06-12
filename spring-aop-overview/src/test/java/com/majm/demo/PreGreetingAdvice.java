package com.majm.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 自定义切面{@link org.aopalliance.aop.Advice} </br>
 * 切面 : 连接点 + 切点
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 23:58
 * @since
 */
@Aspect
public class PreGreetingAdvice {

    /**
     * @Before: 增强类型
     * "execution(* com.majm.demo.NaviWaiter.greet*(..))": 切点表达式
     */
    @Before("execution(* com.majm.demo.NaviWaiter.greet*(..))")
    public void beforeGreeting() { // 增强的 横切逻辑
        System.out.println("How are you!");
    }

}
