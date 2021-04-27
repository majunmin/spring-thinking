package com.majm.source;

import com.majm.domain.A;
import com.majm.domain.B;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 10:28
 * @since
 */
public class BeanCycleDependency {

    @Bean
    public B b() {
        return new B();
    }

    @Bean
    public A a() {
        return new A();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanCycleDependency.class);
        applicationContext.refresh();



        applicationContext.close();
    }


}
