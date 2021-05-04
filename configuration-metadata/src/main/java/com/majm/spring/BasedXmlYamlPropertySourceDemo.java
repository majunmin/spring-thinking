package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 基于XML资源的 yaml 外部化配置示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 22:12
 * @since
 */
//@PropertySource("classpath:/META-INF/user-yaml-context.xml")
public class BasedXmlYamlPropertySourceDemo {

    public static void main(String[] args) {


        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/user-yaml-context.xml");

        // 获取 Mapyaml对象
        Map<String, Object> userMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(userMap);

    }
}
