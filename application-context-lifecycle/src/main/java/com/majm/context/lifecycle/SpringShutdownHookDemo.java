package com.majm.context.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-30 08:36
 * @since
 */
@Slf4j
public class SpringShutdownHookDemo {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(SpringShutdownHookDemo.class);
        context.refresh();
        context.registerShutdownHook();


        System.out.println("input any key to continue: ");
        System.in.read();


        context.close();
    }

    @EventListener
    public void onContextClosed(ContextClosedEvent event) {
        log.info("context closed");
    }
}
