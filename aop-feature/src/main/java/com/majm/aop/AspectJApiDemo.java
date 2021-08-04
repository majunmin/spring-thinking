package com.majm.aop;

import com.majm.aop.config.AspectJConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * AspectJ api demo </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 09:28
 * @since
 */
@Slf4j
public class AspectJApiDemo {

    public static void main(String[] args) {

        // 一个 HashMap 缓存, 作为被代理缓存
        HashMap<Object, Object> cache = new HashMap<>();

        // 创建 ProxyFactory 工厂
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();

        // 指定代理对象
        proxyFactory.setTarget(cache);

        // 添加Aspect 配置
        // AspectJConfig 标注与 @Aspectj 注解
        // addAspect: 自动完成了 切点  增强 和 目标方法的绑定
        proxyFactory.addAspect(AspectJConfig.class);

        // 设置  exposeProxy = true, 暴露代理对象 给 AopContext
        // 目的是为了: 方法内嵌调用也可以被增强
        proxyFactory.setExposeProxy(true);
        // 添加 MethodBeforeAdvice
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                Object proxy = AopContext.currentProxy();
                if ("put".equalsIgnoreCase(method.getName()) && args.length == 2) {
                    log.info("当前存放的是 key: [{}], value: [{}], 代理对象 is [{}]", args[0], args[1], proxy);
                }
            }
        });

        // 添加  AfterReturningAdvice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                if ("put".equalsIgnoreCase(method.getName()) && args.length == 2) {
                    log.info("当前存放的是 key: [{}], current value: [{}], oldValue is : [{}]",
                            args[0], args[1], returnValue);
                }
            }
        });

        // 增加 Aspectj 配置类
        HashMap<String, String> proxy = proxyFactory.getProxy();
        // 通 过代理对象存储数据
        proxy.put("key", "value");
        // target对象直接存储
        cache.put("key", "valuex");
        log.info(proxy.get("key"));


    }
}
