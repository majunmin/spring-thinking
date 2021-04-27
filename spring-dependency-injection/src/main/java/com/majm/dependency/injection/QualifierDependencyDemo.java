package com.majm.dependency.injection;

import com.majm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Qualifier 分组的 功能  和  可以按照名称注入 </br>
 * <p>
 *
 * {@link Qualifier}
 * 1. 分组功能
 * 2. 名称注入
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-15 22:28
 * @since
 */
public class QualifierDependencyDemo {


    @Autowired
    private User user;

    @Autowired
    @Qualifier("superUser")
    private User superUser;

    @Autowired
    @Qualifier
    private List<User> userList;

    @Autowired
    private List<User> allUsers;

    @Bean
    private User user1() {
        User user = new User();
        user.setName("user1");
        return user;
    }

    @Bean
    @Qualifier
    private User user2() {
        User user = new User();
        user.setName("user2");
        return user;
    }

    @Bean
    @Qualifier
    private User user3() {
        User user = new User();
        user.setName("user3");
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierDependencyDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-setter-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        applicationContext.refresh();

        QualifierDependencyDemo demo = applicationContext.getBean(QualifierDependencyDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.userList);
        System.out.println(demo.superUser);
        System.out.println(demo.allUsers);


    }
}
