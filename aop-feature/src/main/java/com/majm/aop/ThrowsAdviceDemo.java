package com.majm.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Random;

/**
 * {@link org.springframework.aop.ThrowsAdvice} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-13 14:13
 * @since
 */
@Slf4j
public class ThrowsAdviceDemo {

    public static void main(String[] args) {
        ThrowsAdviceDemo throwsAdviceDemo = new ThrowsAdviceDemo();
        ProxyFactory proxyFactory = new ProxyFactory(throwsAdviceDemo);
        proxyFactory.addAdvice(new MyThrowsAdvice());
        ThrowsAdviceDemo proxy = (ThrowsAdviceDemo) proxyFactory.getProxy();
        proxy.execute();

    }


    public void execute() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("for propose");
        }
    }
}
