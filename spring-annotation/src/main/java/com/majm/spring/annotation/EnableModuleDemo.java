package com.majm.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 19:46
 * @since
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableModuleDemo.class);
        applicationContext.refresh();

        String helloWorld = applicationContext.getBean(String.class);
        System.out.println(helloWorld);

        applicationContext.close();
    }
}
