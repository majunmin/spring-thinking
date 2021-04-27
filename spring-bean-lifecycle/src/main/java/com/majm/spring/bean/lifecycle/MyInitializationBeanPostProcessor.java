package com.majm.spring.bean.lifecycle;

import com.majm.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-24 11:13
 * @since
 */
public class MyInitializationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == UserHolder.class & "userHolder".equals(beanName)){
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("user holder v");

        }
        return null;
    }
}
