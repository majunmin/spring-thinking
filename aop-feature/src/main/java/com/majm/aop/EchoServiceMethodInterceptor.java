package com.majm.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Echo 拦截器 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 10:20
 * @since
 */
@Slf4j
public class EchoServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        log.info("拦截到的方法是 : " + method.getName());
//        return invocation.proceed();
        return "";
    }
}
