package com.majm.bean.destroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 * Bean 销毁 demo</br>
 *
 * - {@link javax.annotation.PreDestroy}
 * - {@link DisposableBean#destroy()}
 * - 自定义销毁方法
 *   * xml        <bean destroy=""></bean>
 *   * java 注解   @Bean(destroy="")
 *   * java Api   {@link AbstractBeanDefinition#setDestroyMethodName(String)}
 *
 *
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 16:45
 * @since
 */
public class DestroyBeanDemo {

    public static void main(String[] args) {

    }
}
