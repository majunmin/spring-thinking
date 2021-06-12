package com.majm.demo;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 00:01
 * @since
 */
public class AspectJTest {

    @Test
    public void proxy() {
        NaviWaiter waiter = new NaviWaiter();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        // 设置目标对象
        proxyFactory.setTarget(waiter);
        // 添加切面类
        proxyFactory.addAspect(PreGreetingAdvice.class);
        Waiter proxy = proxyFactory.getProxy();
        proxy.greetTo("majm");
        proxy.serveTo("majm");
    }
}
