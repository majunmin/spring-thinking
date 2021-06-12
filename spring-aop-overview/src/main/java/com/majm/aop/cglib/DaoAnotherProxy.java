package com.majm.aop.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 13:43
 * @since
 */
public class DaoAnotherProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始intercept");
        methodProxy.invokeSuper(obj, args);
        System.out.println("结束intercept");
        return obj;
    }
}
