package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于注解的  {@link PropertySource} Yaml 外部化配置示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 22:38
 * @since
 */
@PropertySource(name = "yamlPropertySource", value = "classpath:/META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(19);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedYamlPropertySourceDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean("configuredUser", User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
