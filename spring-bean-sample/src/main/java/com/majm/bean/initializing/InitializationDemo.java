package com.majm.bean.initializing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

/**
 * Bean 初始化 </br>
 *
 * - {@link javax.annotation.PostConstruct} 标注方法
 * - {@link InitializingBean#afterPropertiesSet()}
 * - 自定义初始化方法
 *   * XML 配置      <bean init-method=""></bean>
 *   * java 注解 @Bean(initMethod = "")
 *   * java Api {@link AbstractBeanDefinition#setInitMethodName(String)}
 *
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 16:41
 * @since
 */
public class InitializationDemo {

    public static void main(String[] args) {

    }
}
