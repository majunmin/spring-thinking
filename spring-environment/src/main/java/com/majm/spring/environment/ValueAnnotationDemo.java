package com.majm.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.factory.annotation.Value} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-09 12:44
 * @since
 */
public class ValueAnnotationDemo {

    @Value("${user.name:majm}")
    public String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);
        applicationContext.refresh();

        ValueAnnotationDemo demo = applicationContext.getBean(ValueAnnotationDemo.class);
        System.out.println(demo.userName);

        applicationContext.close();
    }
}
