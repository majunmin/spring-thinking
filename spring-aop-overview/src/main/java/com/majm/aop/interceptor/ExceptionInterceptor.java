package com.majm.aop.interceptor;

import java.lang.reflect.Method;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 10:20
 * @since
 */
public interface ExceptionInterceptor {
    Object exception(Object proxy, Method method, Object[] args);
}
