package com.majm.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

/**
 * {@link LiveBeansView} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-29 20:22
 * @since
 */
public class LiveBeansViewDemo {

    public static void main(String[] args) throws IOException {

        // 添加 LiveBeanView 的 ObjectName 的 domain
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME, "com.majm.context.lifecycle");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


        context.register(LiveBeansViewDemo.class);
        context.refresh();

        System.out.println("please input: ");
        System.in.read();
        context.close();

    }
}
