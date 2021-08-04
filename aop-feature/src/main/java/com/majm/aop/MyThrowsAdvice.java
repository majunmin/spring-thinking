package com.majm.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-13 14:20
 * @since
 */
@Slf4j
public class MyThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing() {
        log.info("hello ?");
    }

    public void afterThrowing(RuntimeException ex) {
        log.info("stackTrace = [{}]", ex.getMessage());
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        log.info("Method: [{}], args: [{}], target: [{}], stackTrace = [{}]", method.getName(), args, target.getClass(), ex.getMessage());
    }
}
