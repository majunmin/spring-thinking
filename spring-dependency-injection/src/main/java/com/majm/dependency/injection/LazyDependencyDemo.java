package com.majm.dependency.injection;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-15 23:01
 * @since
 */
public class LazyDependencyDemo {

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Bean
    private User user() {
        return new User();
    }

    @Bean
    private SuperUser superUser() {
        return new SuperUser();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyDependencyDemo.class);
        applicationContext.refresh();

        LazyDependencyDemo demo = applicationContext.getBean(LazyDependencyDemo.class);
        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();
    }
}
