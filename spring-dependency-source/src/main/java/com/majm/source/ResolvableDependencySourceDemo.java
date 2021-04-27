package com.majm.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 10:35
 * @since
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String str;

    @PostConstruct
    private void init() {
        System.out.println(str);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

        /**
         * BeanFactoryPostProcessor 生命周期回调
         */
        applicationContext.addBeanFactoryPostProcessor((ConfigurableListableBeanFactory beanFactory) -> {
            // 注册 spring 游离对象
            beanFactory.registerResolvableDependency(String.class, "hello World");
        });
        applicationContext.refresh();
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

//        if (beanFactory instanceof ConfigurableBeanFactory) {
//            ConfigurableListableBeanFactory configurableListableBeanFactory = (ConfigurableListableBeanFactory) beanFactory;
//            configurableListableBeanFactory.registerResolvableDependency(String.class, "hello World");
//        }


        applicationContext.close();
    }
}
