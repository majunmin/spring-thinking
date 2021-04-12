package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException} </br>
 * 要查找的 bean  不唯一
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-12 23:50
 * @since
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        User bean = applicationContext.getBean(User.class);

        applicationContext.close();
    }

    @Bean
    public User user1() {
        return new User();
    }

    @Bean
    public User user2() {
        return new User();
    }

    @Bean
    public User user3() {
        return new User();
    }
}
