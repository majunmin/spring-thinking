package com.majm.aop.interceptor;

import java.lang.reflect.Method;

/**
 * Finally 执行拦截 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 10:28
 * @since
 */
public interface FinallyInterceptor {

    void finalize(Object proxy, Method method, Object[] args);

}
