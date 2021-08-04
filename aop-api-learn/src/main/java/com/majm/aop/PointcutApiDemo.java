package com.majm.aop;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 23:33
 * @since
 */
public class PointcutApiDemo {

    public static void main(String[] args) {

//        EchoServiceEchoMethodPointcut echoServicePointcut = EchoServiceEchoMethodPointcut.INSTANCE;
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        // Comosable 模式
        ComposablePointcut composablePointcut = new ComposablePointcut(EchoServiceEchoMethodPointcut.INSTANCE);
        composablePointcut.intersection(echoServicePointcut.getClassFilter());
        composablePointcut.intersection(echoServicePointcut.getMethodMatcher());


        // 方法拦截 类似于  Around Advice
        EchoServiceMethodInterceptor advice = new EchoServiceMethodInterceptor();
        // pointcut 不能直接放入  ProxyFactory，需要是配成 Advisor(pointcut + advice)
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(composablePointcut, advice);

        // target Object
        DefaultEchoService echoService = new DefaultEchoService();
        // Proxyfactory 进行织入
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(echoService);
        proxyFactory.addAdvisor(advisor);
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        proxy.echo("Hello world1");


    }
}
