package com.majm.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-14 15:24
 * @since
 */
@Slf4j
public class ProxyCreateSupportDemo {

    public static void main(String[] args) {
        DefaultEchoService echoService = new DefaultEchoService();

        ProxyFactory proxyFactory = new ProxyFactory();
        /// 设置 目标对象
        proxyFactory.setTarget(echoService);
        // 添加增强 advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());

        // 添加监听器  AdvisedSupportListener
        proxyFactory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                log.info("AOP 配置对象 [{}] 已激活", advised);
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {

            }
        });


        // 激活事件触发 createAopProxy()  <- getProxy()
        // proxyFactory.getProxy()
        // 底层调用的就是  ProxyCreatorSupport#createAopProxy()#getProxy()
        EchoService echoServiceProxy = (DefaultEchoService)proxyFactory.getProxy();
        System.out.println(echoServiceProxy.echo("Hellow world!"));
    }
}
