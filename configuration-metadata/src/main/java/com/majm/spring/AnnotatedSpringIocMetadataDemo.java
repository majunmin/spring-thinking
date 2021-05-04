package com.majm.spring;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

/**
 * springIOC  容器元信息配置
 * </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 08:40
 * @since
 */
//@ImportResource("classpath:/META-INF/")
@Import({User.class, SuperUser.class})
public class AnnotatedSpringIocMetadataDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedSpringIocMetadataDemo.class);
        applicationContext.refresh();

        Map<String, User> usersMap = applicationContext.getBeansOfType(User.class);
        usersMap.forEach((key, val) -> System.out.printf("User bean name: %s, content: %s \n", key, val));


        applicationContext.close();
    }
}
