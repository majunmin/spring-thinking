package com.majm.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * 一句话功能简述 </br>
 * <p>
 * {@link  Profile}
 * {@link  Environment}
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-09 00:28
 * @since
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ProfileDemo.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setDefaultProfiles("odd");  // 默认 Profile = ["odd"]
        environment.setActiveProfiles("even");

        applicationContext.refresh();

        Integer number = applicationContext.getBean("number", Integer.class);

        System.out.println(number);
        applicationContext.close();
    }

    @Profile("odd")
    @Bean("number")
    public Integer odd() {
        return 1;
    }

//    @Profile("even")
    @Bean("number")
    @Conditional(EvenCondition.class)
    public Integer event() {
        return 2;
    }
}
