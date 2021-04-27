package com.majm.source;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源 Demo </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 09:37
 * @since
 */
public class DependencySourceDemo {

    // 这些对象的注入 在 `AbstractApplicationContext.prepareBeanFactory` 中, 早于 settter注入, 早于  PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    private void initByDI() {
        System.out.println("applicationContext == beanFactory: " + (applicationContext == beanFactory));
        System.out.println("applicationContext.getAutowireCapableBeanFactory() == beanFactory: " + (applicationContext.getAutowireCapableBeanFactory() == beanFactory));
        System.out.println("applicationContext == resourceLoader: " + (applicationContext == resourceLoader));
        System.out.println("applicationContext == applicationEventPublisher: " + (applicationContext == applicationEventPublisher));

    }

    @PostConstruct
    private void initByLookUp() {

        System.out.println(getBean(BeanFactory.class));
        System.out.println(getBean(ApplicationContext.class));
        System.out.println(getBean(ApplicationEventPublisher.class));
        System.out.println(getBean(ResourceLoader.class));

    }

    private Object getBean(Class<?> clazz) {
        Object bean = null;
        try {
            bean = applicationContext.getBean(clazz);
        } catch (BeansException e) {
            System.out.println("找不到 bean : " + clazz);
        }
        return bean;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();


        applicationContext.close();
    }
}
