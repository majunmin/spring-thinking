package com.majm.spring.environment;

import com.majm.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link PropertyPlaceholderConfigurer}处理属性占位符 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-09 11:38
 * @since
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/placeholder-resolver.xml");
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);


        applicationContext.close();

    }
}
