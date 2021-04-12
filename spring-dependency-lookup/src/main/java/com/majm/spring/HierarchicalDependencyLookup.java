package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

/**
 * 层次性依赖查找 {@link HierarchicalBeanFactory} </br>
 * <p>
 * {@link HierarchicalBeanFactory#containsLocalBean(String)} 查看 本 BeanFactory 是否包含 beanName, 不包含  parentBeanFactory
 * {@link HierarchicalBeanFactory#containsBean(String)}      查看 BeanFactory 是否包含 beanName, 包含 parentBeanFactory
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-12 21:20
 * @since
 */
public class HierarchicalDependencyLookup {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookup.class);
        // 启动容器上下文
        applicationContext.refresh();

        // ConfigurableListableBeanFactory  -> ConfigurableBeanFactory  -> HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        System.out.println(beanFactory.getParentBeanFactory());

        // 设置 parentBeanFactory
        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println(beanFactory.getParentBeanFactory());

        displayLocalBean(beanFactory, "user1");
        displayLocalBean(parentBeanFactory, "user1");

        displayBean(beanFactory, "user1");
        displayBean(parentBeanFactory, "user1");


    }

    private static void displayBean(ConfigurableListableBeanFactory beanFactory, String beanName) {
        if (Objects.nonNull(beanFactory)) {
            System.out.printf("beanFactory: [%s] 是否包含 bean: [%s] ==> %s \n", beanFactory, beanName, containsBean(beanFactory, beanName));
        }
    }

    public static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        if (Objects.nonNull(beanFactory)) {
            System.out.printf("beanFactory: [%s] 是否包含 bean: [%s] ==> %s \n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
        }
    }


    @Bean
    public User user() {
        User user = new User();
        user.setName("majm");
        return user;
    }

    public static ConfigurableListableBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        listableBeanFactory.registerBeanDefinition("user1", generateBeanDefinition());
        return listableBeanFactory;
    }

    public static BeanDefinition generateBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.addPropertyValue("name", "majm1")
                .addPropertyValue("age", 18)
                .getBeanDefinition();
        return beanDefinition;
    }
}
