package com.majm.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 自定义 PointCut </br>
 * <p>
 * {@link ClassFilter}
 * {@link MethodMatcher}
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 23:26
 * @since
 */
public class EchoServiceEchoMethodPointcut implements Pointcut {

    /**
     * 这种 代码里写死的 可以使用一下单例
     */
    public static final EchoServiceEchoMethodPointcut INSTANCE = new EchoServiceEchoMethodPointcut();

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return EchoService.class.isAssignableFrom(clazz); // EchoService 接口及其子接口 及其实现类都会匹配
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return ClassUtils.isAssignable(EchoService.class, targetClass)
                        && Objects.equals(method.getName(), "echo") && method.getParameterCount() == 1
                        && Objects.equals(String.class, method.getParameterTypes()[0]);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
