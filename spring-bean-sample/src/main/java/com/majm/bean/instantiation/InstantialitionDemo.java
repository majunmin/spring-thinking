package com.majm.bean.instantiation;

/**
 * 实例化 Bean  </br>
 *
 * - 常规方式
 *   * 构造器             (配置元信息   XML java注解  javaApi)
 *   * 通过静态工厂方法     (配置元信息   XML java注解  javaApi)
 *   * 通过 Bean工厂方法   (配置元信息   XML java注解  javaApi)
 *   * 通过 FactoryBean  (配置元信息   XML java注解  javaApi)
 * - 特殊方式
 *   * 通过 ServiceLoaderFactoryBean  (配置元信息   XML java注解  javaApi)
 *   * 通过 AutowireCapableBeanFactory#createBean()
 *   * 通过 BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 15:47
 * @since
 */
public class InstantialitionDemo {

    public static void main(String[] args) {

    }
}
