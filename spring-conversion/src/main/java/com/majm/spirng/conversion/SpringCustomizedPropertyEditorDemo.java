package com.majm.spirng.conversion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 13:53
 * @since
 */
public class SpringCustomizedPropertyEditorDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringCustomizedPropertyEditorDemo.class);
        applicationContext.register(CustomPropertyEditorRegistrar.class);

        // AbstractApplicationContext  --> "conversionService" ConverterService Bean
        // --> ConfigurableBeanFactory#setConversionService()   -->  BeanFactory#getConversionServcie()   --> BeanDefinition  --> BeanWrapper


        // BeanDefinition --> BeanWrapper --> 属性转换(数据来源 PropertyValues)
        // setPropertyValues(PropertyValues)  --> TypeConverter#convertIfNecessory()
        // TypeConverterDelegate#convertIfNecessory  --> PropertyEditor  or ConversionService



        applicationContext.refresh();
        applicationContext.close();
    }
}
