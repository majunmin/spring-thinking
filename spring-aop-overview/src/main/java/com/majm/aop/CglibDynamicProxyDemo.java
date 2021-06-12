package com.majm.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 21:44
 * @since
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 指定 superClass  is DefaultEchoService
        enhancer.setSuperclass(DefaultEchoService.class);
//        enhancer.setCallbackFilter();
        enhancer.setCallback(new MethodInterceptor() {
            /**
             *
             * @param source cglib 生成的代理子类对象
             * @param method  父类 为增强方法  (method.invoke(new DefaultEchoService(), args);)
             * @param args  方法参数
             * @param methodProxy  cglib生成的子类 代理方法
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Instant startTime = Instant.now();

                // 错误数的使用方式: 这里会造成递归调用  --> java.lang.StackOverflowError
                Object result = method.invoke(source, args);
                // 正确的使用方式 调用 父类 DefaultEchoService的方法
//                Object result = methodProxy.invokeSuper(source, args);
                System.out.println("[cglib] echo() execute cost(ms):  " + Duration.between(startTime, Instant.now()).toMillis());
                return result;
            }
        });

        EchoService echoService = (EchoService) enhancer.create();
        echoService.echo("hello world");
    }
}
