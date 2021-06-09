package com.majm.spring.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.sound.sampled.Line;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link org.springframework.core.env.Environment} 配置属性变更示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-11 23:07
 * @since
 */
@Slf4j
public class EnvironmentPropertySourceChangedDemo {

    @Value("${user.name}")
    private String userName;


    public static void main(String[] args) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(EnvironmentPropertySourceChangedDemo.class);

        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "马俊民");
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // environment.getPropertySources() 前面的优先级高, FIFO
        environment.getPropertySources().addFirst(new MapPropertySource("first-property-source", map));
        applicationContext.refresh();

        map.put("user.name", "majm");  // 这里的属性改变可以改变了 PropertySource 里面的值, 但是改变不了 @Value 的行为
        // 遍历打印 environment.getPropertySources()
        environment.getPropertySources().forEach(propertySource -> {
            //  propertySourceName = first-property-source,  user.name = 马俊民
            //  propertySourceName = systemProperties,  user.name = majunmin
            //  propertySourceName = systemEnvironment,  user.name = null
            log.info("propertySourceName = {},  user.name = {}", propertySource.getName(), propertySource.getProperty("user.name"));
        });

        EnvironmentPropertySourceChangedDemo demo = applicationContext.getBean(EnvironmentPropertySourceChangedDemo.class);
        log.info(demo.userName);

        applicationContext.close();
    }
}
