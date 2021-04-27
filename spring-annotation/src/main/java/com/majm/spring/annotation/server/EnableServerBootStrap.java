package com.majm.spring.annotation.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 23:15
 * @since
 */
@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootStrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableServerBootStrap.class);
        applicationContext.refresh();

        Server server = applicationContext.getBean(Server.class);
        server.start();
        server.stop();


        applicationContext.close();
    }
}
