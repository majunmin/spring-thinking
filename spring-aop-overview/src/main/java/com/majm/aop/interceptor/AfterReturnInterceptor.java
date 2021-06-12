package com.majm.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 10:19
 * @since
 */
public interface AfterReturnInterceptor {

    /**
     * @param proxy
     * @param method
     * @param args
     * @param result
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object result);
}
