package com.majm.bean.definition;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 14:25
 * @since
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        // 1. BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 属性设置
        beanDefinitionBuilder.addPropertyValue("name", "majm")
                .addPropertyValue("age", 18);

        // 获取 BeanDefinition 实例
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非 Bean 的最终状态,可以自定义修改

        // 2. AbstractBeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(SuperUser.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("name", "majm");
//        propertyValues.addPropertyValue("age", 18);
//        propertyValues.addPropertyValue("address", "bj");
        propertyValues.add("name", "MAJM")
                .add("age", 18)
                .add("address", "bj");

        genericBeanDefinition.setPropertyValues(propertyValues);


    }
}
