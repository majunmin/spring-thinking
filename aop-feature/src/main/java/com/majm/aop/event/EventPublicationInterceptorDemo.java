package com.majm.aop.event;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventPublicationInterceptor;

import java.lang.reflect.Method;

/**
 * {@link EventPublicationInterceptor} sample</br>
 * {@link EventPublicationInterceptor} 实现了接口  {@link MethodInterceptor} {@link ApplicationEventPublisherAware}</br>
 * {@link EventPublicationInterceptor} 可以实现 增强某一个方法,指定方法执行完成后发布指定事件{@link ApplicationEvent}
 *
 * @author majunmin
 * @description
 * @datetime 2021-07-18 17:24
 * @since
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
public class EventPublicationInterceptorDemo {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Executor.class, StaticExecutor.class, EventPublicationInterceptorDemo.class);
        applicationContext.refresh();

        // 方法执行
        final Executor executor = applicationContext.getBean("executor", Executor.class);
        final StaticExecutor staticExecutor = applicationContext.getBean("staticExecutor", StaticExecutor.class);

        executor.execute();
        staticExecutor.execute();


        applicationContext.close();
    }

    @Bean
    public Pointcut pointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "execute".equals(method.getName()) && StaticExecutor.class == targetClass;
            }
        };
    }

    // 托管给 spring 容器， EventPublicationInterceptor 有一些 spring生命周期回调方法
    @Bean
    public EventPublicationInterceptor eventPublicationInterceptor() {
        final EventPublicationInterceptor interceptor = new EventPublicationInterceptor();
        interceptor.setApplicationEventClass(ExecuteEvent.class);
        return interceptor;
    }

    @Bean
    public PointcutAdvisor advisor(Pointcut pointcut, EventPublicationInterceptor eventPublicationInterceptor) {
        // EventPublicationInterceptor is MethodInterceptor (advice)
        return new DefaultPointcutAdvisor(pointcut(), eventPublicationInterceptor);
    }

    @EventListener(value = ExecuteEvent.class)
    public void onEvent(ExecuteEvent event) {
        log.info("receive event : [{}], execute callback...", event);
    }


}
