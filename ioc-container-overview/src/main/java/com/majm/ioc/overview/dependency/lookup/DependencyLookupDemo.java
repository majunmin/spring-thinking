package com.majm.ioc.overview.dependency.lookup;

import com.majm.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 依赖查找示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-10 18:59
 * @since
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencyLookupDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("majm");
        user.setAge(18);
        return user;
    }
}
