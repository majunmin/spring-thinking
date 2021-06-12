package com.majm.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 基于注解实现  AspectJ </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 08:49
 * @since
 */
@Aspect
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
public class AspectJAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectJAnnotationDemo.class);
        applicationContext.refresh();


        AspectJAnnotationDemo aspectJAnnotationDemo = applicationContext.getBean(AspectJAnnotationDemo.class);
        System.out.println(aspectJAnnotationDemo);

        applicationContext.close();
    }


}
