package com.majm.spring.bean.lifecycle;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import com.majm.domain.UserHolder;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.ObjectUtils;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        /**
         * bean实例化前执行(构造方法执行之前)
         * 如果该方法不返回 null, 则后续的 doCreateVewan() 和  bean初始化方法 不会执行, 会返回给spring容器此处的代理bean
         *
         * @param beanClass
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            // 覆盖配置完整的  superUser
            if (ObjectUtils.nullSafeEquals(beanName, "superUser")) {
                return new SuperUser();
            }
            return null;
        }


        /**
         * {@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)} 中调用
         *
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (User.class == bean.getClass()) {
                // user对象不允许属性赋值(元信息配置)
                return false;
            }
            return true;
        }

        /**
         * @param pvs
         * @param bean
         * @param beanName
         * @return
         * @throws BeansException
         */
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {

            final MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
                propertyValues.addPropertyValues(pvs);
            }

            // 对 userHolder bean进行拦截
            if ("userHolder".equals(beanName) && bean.getClass() == UserHolder.class) {
                propertyValues.addPropertyValue("number", 1);
                propertyValues.addPropertyValues(pvs);

                if (propertyValues.contains("description")) {
                    propertyValues.removePropertyValue("description");
                }
                propertyValues.addPropertyValue("description", "hello");

                return propertyValues;
            }


            return null;
        }
    }
