package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 类型安全的依赖查找 </br>
 * BeanFactory#getBean()
 * ObjectFactory#getObject()
 * ObjectProvider#getIfAvailable()
 * ObjectProvider#stream()
 * ListableBeanFactory#getBeansOfType()
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-12 22:26
 * @since
 */
public class TypeSafetyDependencyLookup {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TypeSafetyDependencyLookup.class);

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetIfAvailable(applicationContext);
        displayObjectProviderStream(applicationContext);
        displayListableFactoryGetBeansOfType(applicationContext);
        applicationContext.close();
    }

    private static void displayListableFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printStackTrace(() -> {
            Map<String, User> beansOfType = beanFactory.getBeansOfType(User.class);
        }, "displayListableFactoryGetBeansOfType");
    }

    private static void displayObjectProviderStream(BeanFactory beanFactory) {
        printStackTrace(() -> {
            ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
            beanProvider.stream().forEach(System.out::println);
        }, "displayObjectProviderGetIfAvailable");
    }

    private static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
        printStackTrace(() -> {
            ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
            beanProvider.getIfAvailable();
        }, "displayObjectProviderGetIfAvailable");
    }

    private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        printStackTrace(() -> {
            ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
            beanProvider.getObject();
        }, "displayObjectProviderGetObject");
    }


    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printStackTrace(() -> beanFactory.getBean(User.class), "displayBeanFactoryGetBean");
    }

    private static void printStackTrace(Runnable runnable, String source) {
        System.err.println("========================================================================");
        System.err.println("source : " + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }


}
