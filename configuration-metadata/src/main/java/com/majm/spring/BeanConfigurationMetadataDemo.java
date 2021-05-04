package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Bean配置元信息 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-24 19:50
 * @since
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {

        // 构建 BeanDefinition 定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "majm");

        // 获取 BeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 附加属性  (不影响bean的生成 populateBean() initializeBean())
        beanDefinition.setAttribute("name", "马俊民");

        // 当前bean来源于何方  辅助作用
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 user BeanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if ("user".equals(beanName) && User.class.isAssignableFrom(bean.getClass())) {
                    BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition("user");
                    if (userBeanDefinition.getSource() == BeanConfigurationMetadataDemo.class) { // 通过source判断 BeanDefinition 来源

                        String name = (String) userBeanDefinition.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return null;
            }
        });

        User user = beanFactory.getBean(User.class);
        // User{id=null, name='majm', age=null, city=null}
        System.out.println(user);
    }

}
