package com.majm.aop;

import com.majm.aop.pointcut.EchoServicePointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * PointCut api 方式的使用</br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 11:10
 * @since
 */
public class PointcutApiDemo {

    public static void main(String[] args) {

        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);

        // 方法拦截 类似于  Around Advice
        EchoServiceMethodInterceptor advice = new EchoServiceMethodInterceptor();
        // pointcut 不能直接放入  ProxyFactory，需要是配成 Advisor(pointcut + advice)
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        DefaultEchoService echoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(echoService);
        proxyFactory.addAdvisor(advisor);
//        proxyFactory.addAdvice(advice);
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        proxy.echo("hello World!");


    }
}
