package com.majm.aop.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 13:46
 * @since
 */
public class DaoFilter implements CallbackFilter {
    // 返回数值表示顺序
    @Override
    public int accept(Method method) {
        if ("select".equalsIgnoreCase(method.getName())) {
            return 0;
        } else if ("insert".equalsIgnoreCase(method.getName())) {
            return 1;
        }
        return 2;
    }
}
