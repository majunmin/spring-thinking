package com.majm.spring.bean.lifecycle;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * {@link BeanDefinition} 合并示例</br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 22:17
 * @since
 */
public class BeanDefinitionDemo {

    public static void main(String[] args) {

        String location = "classpath:/META-INF/user-context.xml";
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(location);

        User user = beanFactory.getBean(User.class);
        User superUser = beanFactory.getBean(SuperUser.class);


    }
}
