package com.majm.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 前置拦截器 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 09:13
 * @since
 */
public interface BeforeInterceptor {

    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
