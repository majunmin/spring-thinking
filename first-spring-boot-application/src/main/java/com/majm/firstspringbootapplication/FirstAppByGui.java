package com.majm.firstspringbootapplication;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 00:27
 * @since
 */
@SpringBootApplication
public class FirstAppByGui {

    public static void main(String[] args) {

    }

    /**
     * ApplicationRunner#run() 在
     * springBoot应用启动后回调
     * <p>
     *
     * @param context
     * @return
     */
    public ApplicationRunner runner(WebServerApplicationContext context) {
        return args -> {
            System.out.println("当前 webServer 实现类是: " + context.getWebServer().getClass().getCanonicalName());
        };
    }
}
