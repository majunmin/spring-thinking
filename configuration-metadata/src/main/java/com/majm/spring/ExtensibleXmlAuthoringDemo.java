package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * spring XML 扩展元素 Demo</br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-25 23:36
 * @since
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        // 创建IOC底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建读取 XML资源的 BeanDefinitionReader
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 加载 xml资源
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/user-context.xml");


        User user = beanFactory.getBean(User.class);
        System.out.println(user);

    }
}
