package com.majm.bean.register;

import com.majm.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

/**
 * Bean 注册到 spring 容器的方式 </br>
 * 1. 配置元信息
 * xml
 * 2. 注解的方式
 * - @Bean
 * - @Component
 * - @Import
 * 3. Java Api 的方式
 * - 命名方式  BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)
 * - 非命名方式  BeanDefinitionReaderUtils#registerWithGeneratedName(BeanDefinition, BeanDefinitionRegistry)
 * - 配置类方式  AnnotatedBeanDefinitionReader#register(Class...)
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 15:15
 * @since
 */
public class BeanRegisterDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config.class);
        applicationContext.refresh();

        registerUserBeanDefinition(applicationContext, "user1");
        registerUserBeanDefinition(applicationContext);

        System.out.println("Config 类型的 所有 Bean :" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的 所有 Bean :" + applicationContext.getBeansOfType(User.class));


        applicationContext.close();
    }


    @Configuration
    private static class Config {

        public Config() {
        }

        @Bean
        public User user() {
            User user = new User();
            user.setName("majm");
            user.setAge(18);
            return user;
        }
    }


    /**
     * 非命名方式 注册 Bean
     * @param registry
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {

        registerUserBeanDefinition(registry, null);
    }



    /**
     * 命名方式 注册 Bean
     * @param registry
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "majm").addPropertyValue("age", 18);

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }

    }


}
