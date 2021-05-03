package com.majm.spring.event;

import com.majm.spring.event.listener.CustomEvent1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 注解驱动 spirng 异步事件处理 {@link  EnableAsync}  </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-30 13:28
 * @since
 */
@Slf4j
@EnableAsync // 激活spring异步特性
public class AnnotatedAsyncEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedAsyncEventListenerDemo.class);

        applicationContext.refresh();

        // spring 容器发布事件
        applicationContext.publishEvent(new CustomEvent1("this is a customEvent1"));

        // spring 容器上下文关闭
        applicationContext.close();
    }

    @Async
    @EventListener
    public void onEvent(CustomEvent1 event) {
        log.info("[{}]线程 监听到事件: {} \n", Thread.currentThread().getName(), event);
    }


    @Bean
    public Executor taskExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("async-thread-pool"));
        return executor;
    }
}
