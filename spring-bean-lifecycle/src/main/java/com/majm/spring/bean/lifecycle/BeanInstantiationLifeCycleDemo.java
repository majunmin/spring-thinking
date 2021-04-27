package com.majm.spring.bean.lifecycle;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import com.majm.domain.UserHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-20 22:38
 * @since
 */
public class BeanInstantiationLifeCycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("-----------------------");
        executeApplicationContext();
    }

    private static void executeApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInstantiationLifeCycleDemo.class);
        applicationContext.register(UserHolder.class);
        // applicationContext 注册 BeanPostProcessor
        applicationContext.addBeanFactoryPostProcessor((ConfigurableListableBeanFactory beanFactory) ->{
            beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        });
        applicationContext.refresh();


        applicationContext.getBean(UserHolder.class);
        applicationContext.close();
    }

    private static void executeBeanFactory() {
        String location = "classpath:/META-INF/user-context.xml";
        String location2 = "classpath:/META-INF/bean-constructor-denpendency-injection.xml";
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanPostProcessor
//        beanFactory.addBeanPostProcessor(new ApplicationContextAware(beanFactory));
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(location, location2);

        SuperUser user = beanFactory.getBean(SuperUser.class);
        System.out.println(user);


        // 构造器注入是按照  有Primary 按照类型注入， 否则按照名称注入  resolveDependency()
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }

    @Bean
    public User user(){
        return new User();
    }
}
