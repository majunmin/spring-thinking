package com.majm.spring.event.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link EnableAsync} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-30 19:35
 * @since
 */
@Slf4j
@EnableAsync
public class TestAsyncDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyService.class);
        applicationContext.registerBeanDefinition("myService", beanDefinitionBuilder.getBeanDefinition());
        applicationContext.register(TestAsyncDemo.class);
        applicationContext.refresh();
        applicationContext.start();

        MyService service = applicationContext.getBean(MyService.class);
        service.testMethod();

        log.info("MyService#testMethod() async...");


        applicationContext.close();
    }
}
