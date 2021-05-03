package com.majm.spring.i18n;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * SpringBoot 场景下 自定义 {@link MessageSource} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-30 00:18
 * @see MessageSource
 * @see org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
 * @see org.springframework.context.support.ReloadableResourceBundleMessageSource
 * @since
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceDemo {

    /**
     * SpringBoot场景中  Primary ConfigurationClass 优先级高于   AutoConfiguration
     * (注解驱动是先进先出的)
     *
     * @return
     */
//    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
//    public MessageSource messageSource() {
//        return new ReloadableResourceBundleMessageSource();
//    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceDemo.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            // 查找 messageSource 的 BeanDefinition
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME);
            System.out.println(beanDefinition);

            // 查找 messageSource  Bean
            MessageSource messageSource =
                    applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }


        // 关闭应用上下文
        applicationContext.close();
    }
}
