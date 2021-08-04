package com.majm.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 方法拦截 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 23:34
 * @since
 */
@Slf4j
public class EchoServiceMethodInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("EchoServiceMethodInterceptor execute...");
        Object result = invocation.proceed();
        // after...
        return result;
    }
}
