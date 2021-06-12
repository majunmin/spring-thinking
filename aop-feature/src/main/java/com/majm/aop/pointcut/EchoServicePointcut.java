package com.majm.aop.pointcut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * {@link org.springframework.aop.Pointcut} APi实现 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 11:07
 * @since
 */
@Getter
@Setter
@AllArgsConstructor
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

    private String methodName;

    private Class<?> targetClass;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {


        return Objects.equals(method.getName(), getMethodName())
                && ClassUtils.isAssignable(getTargetClass(), targetClass);
    }
}
