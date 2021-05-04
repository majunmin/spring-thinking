package com.majm.spring.validator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * SpringBeanValidator 整合示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 09:02
 * @see Validator
 * @see LocalValidatorFactoryBean
 * @see MethodValidationPostProcessor
 * @since
 */
public class SpringBeanValidatorDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringBeanValidatorDemo.class);
        applicationContext.registerBeanDefinition("LocalValidatorFactoryBean",
                BeanDefinitionBuilder.genericBeanDefinition(LocalValidatorFactoryBean.class).getBeanDefinition());
        applicationContext.addBeanFactoryPostProcessor(new BeanFactoryPostProcessor() {
            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                beanFactory.addBeanPostProcessor(new MethodValidationPostProcessor());
            }
        });
        applicationContext.refresh();

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        userProcessor.processUser(new User());


        applicationContext.close();

    }

    @Bean
    public UserProcessor userProcessor(){
        return new UserProcessor();
    }

    @Component
    @Validated
    static class UserProcessor {

        public void processUser(@Valid User user) {
            System.out.println(user);
        }

    }

    static class User {
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
