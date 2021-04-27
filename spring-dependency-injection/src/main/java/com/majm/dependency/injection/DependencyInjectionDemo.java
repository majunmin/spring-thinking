package com.majm.dependency.injection;

import com.majm.domain.UserHolder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-15 08:55
 * @since
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        String configLocation = "classpath:/META-INF/dependency-setter-injection.xml";
        BeanFactory applicationContext = new ClassPathXmlApplicationContext(configLocation);

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder.getUser());
    }
}
