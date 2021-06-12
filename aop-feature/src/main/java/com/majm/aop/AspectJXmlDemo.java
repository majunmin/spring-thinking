package com.majm.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link org.springframework.aop.framework.ProxyFactory} </br>
 * {@link org.springframework.aop.framework.ProxyFactoryBean} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 08:58
 * @since
 */
@Configuration(proxyBeanMethods = false)
//@Component
public class AspectJXmlDemo {


    public static void main(String[] args) {
        String location = "classpath:/META-INF/spring-aop-context.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);
        EchoService echoService = applicationContext.getBean("echoServiceFactoryBean", EchoService.class);

        echoService.echo("Hello world!");
        applicationContext.close();
    }


}
