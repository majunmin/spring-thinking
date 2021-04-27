package com.majm.spring.bean.lifecycle;

import com.majm.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean元信息读取</br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 19:51
 * @since
 */
public class BeanMetaDataConfigurationDemo {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(BeanMetaDataConfigurationDemo.class);
//        applicationContext.refresh();


        String location = "META-INF/default.properties";
        ClassPathResource classPathResource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(classPathResource, "UTF-8");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的 BeanDefinition 数量: " + beanDefinitions);

        User user = beanFactory.getBean(User.class);
        System.out.println(user);


//        applicationContext.close();
    }
}
