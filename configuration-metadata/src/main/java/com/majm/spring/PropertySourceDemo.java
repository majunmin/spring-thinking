package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-26 00:21
 * @since
 */
@PropertySource("classpath:/META-INF/user-bean-context.properties")
@PropertySource("classpath:/META-INF/user-bean-context.properties")// java8 + @Repeatable
//@PropertySources(@PropertySource(...))  Java8
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册当前类为 ConfigurationClass
        applicationContext.register(PropertySourceDemo.class);

        // 扩展 spring 的Environment
        // 添加 PropertySource 必须在 refresh() 方法完成之前
        Map<String, Object> propertySource = new HashMap<>();
        propertySource.put("user.name", "changlong");
        org.springframework.core.env.PropertySource mapPropertySource = new MapPropertySource("first-property-source", propertySource);
        applicationContext.getEnvironment().getPropertySources().addFirst(mapPropertySource);
        applicationContext.refresh();

        Map<String, User> usersMap = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            // User Bean name : user , content : User{id=3, name='changlong', age=null, city=null}
            System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
        }

        // [MapPropertySource {name='first-property-source'},
        //  PropertiesPropertySource {name='systemProsperties'},
        //  SystemEnvironmentPropertySource {name='systemEnvironment'},
        //  ResourcePropertySource {name='class path resource [META-INF/user-bean-context.properties]'}]
        System.out.println(applicationContext.getEnvironment().getPropertySources());

        applicationContext.close();
    }
}
