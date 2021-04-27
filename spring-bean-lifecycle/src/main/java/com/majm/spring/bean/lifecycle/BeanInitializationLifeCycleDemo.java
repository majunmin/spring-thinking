package com.majm.spring.bean.lifecycle;

import com.majm.domain.User;
import com.majm.domain.UserHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-24 11:12
 * @since
 */
public class BeanInitializationLifeCycleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.addBeanFactoryPostProcessor((ConfigurableListableBeanFactory beanFactory) -> {
            beanFactory.addBeanPostProcessor(new MyInitializationBeanPostProcessor());
        });
        applicationContext.register(BeanInitializationLifeCycleDemo.class);
        applicationContext.register(UserHolder.class);
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        applicationContext.close();
    }

    @Bean
    public User user(){
        return new User();
    }
}
