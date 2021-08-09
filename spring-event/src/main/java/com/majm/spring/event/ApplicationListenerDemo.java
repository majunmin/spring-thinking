package com.majm.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-03 10:18
 * @since
 */
@Slf4j
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationListenerDemo.class);
        // 基于接口实现
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                log.info("[{}] 接收到 spring事件: [{}]", Thread.currentThread().getName(), event);
            }
        });
        applicationContext.refresh();

        // ApplicationEventMulticaser
        applicationContext.start();
        // 应用上下文关闭
        applicationContext.close();
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        log.info("[{}],  @EventListener(onApplicationEvent1) 接收到 spring ContextRefreshedEvent 事件", Thread.currentThread().getName());
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent2(ContextStartedEvent event) {
        log.info("[{}] @EventListener(onApplicationEvent2) 接收到 spring ContextStartedEvent 事件", Thread.currentThread().getName());
    }

    @Async
    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("[{}] @EventListener 接收到 spring ContextClosedEvent 事件", Thread.currentThread().getName());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // PayloadApplicationEvent
        applicationEventPublisher.publishEvent("Hello world!");
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello world!") {
        });
    }
}
