package com.majm.aop;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Predicated </br>
 * 类 方法  注解 异常  (PointCut)要干的事情
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 08:51
 * @since
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String className = "com.majm.aop.EchoService";
        // obtain  classLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = classLoader.loadClass(className);
        Method targetMethod = ReflectionUtils.findMethod(clazz, "echo", String.class);
        System.out.println(targetMethod);

        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出  NullPointException 的方法是：" + method.getName());
            }
        }, new ReflectionUtils.MethodFilter() {
            //  做一些方法过滤
            // 这里 也可以做一些  通配符的匹配,类似  AspectJ 的方式
            @Override
            public boolean matches(Method method) {
                Class<?>[] exceptionTypes = method.getExceptionTypes();
                Class<?>[] parameterTypes = method.getParameterTypes();
                return exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0])
                        && parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0]);
            }
        });
    }

}
