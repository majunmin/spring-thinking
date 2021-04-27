package com.majm.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 20:04
 * @since
 */
public class AnnotatedBeanDefinitionParserDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于 java注解的 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类 (not Component)
        beanDefinitionReader.register(AnnotatedBeanDefinitionParserDemo.class);
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount() - beanDefinitionBefore;
        System.out.println("已加载的 BeanDefinition 数量: " + beanDefinitionAfter);

        // 普通的 Class 作为 Component 注册到 SpringIOC容器,
        // beanName 的生成来自于  BeanNameGenerator, 注解实现是   AnnotationBeanNameGenerator
        AnnotatedBeanDefinitionParserDemo demo = beanFactory.getBean("annotatedBeanDefinitionParserDemo", AnnotatedBeanDefinitionParserDemo.class);
        System.out.println(demo);


    }
}
