package com.majm.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Objects;

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