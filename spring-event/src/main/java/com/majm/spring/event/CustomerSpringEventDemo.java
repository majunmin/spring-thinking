package com.majm.spring.event;

import com.majm.spring.event.listener.CustomEvent1;
import com.majm.spring.event.listener.CustomEvent2;
import com.majm.spring.event.listener.CustomerApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 SpringEvent 处理 </br>
 * <p>
 * {@link ApplicationListener}
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 20:10
 * @since
 */
public class CustomerSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        // 1. 添加自定义 事件监听器 ApplicationListener
        applicationContext.addApplicationListener(new CustomerApplicationListener());

        // 2. 添加自定义 事件监听器 ApplicationListener
        applicationContext.addApplicationListener(new ApplicationListener<CustomEvent2>() {
            @Override
            public void onApplicationEvent(CustomEvent2 event) {
                System.out.println(event.getClass().getCanonicalName());
            }
        });


        applicationContext.refresh();
        applicationContext.publishEvent(new CustomEvent1("applicationContext"));
        applicationContext.publishEvent(new CustomEvent2("applicationContext"));

        applicationContext.close();


    }
}
