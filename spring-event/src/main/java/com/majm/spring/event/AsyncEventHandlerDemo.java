package com.majm.spring.event;

import com.majm.spring.event.listener.CustomEvent1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件处理 </br>
 * <p>
 * {@link SimpleApplicationEventMulticaster#multicastEvent(ApplicationEvent, ResolvableType)}
 * ```
 *    @Override
 *    public void multicastEvent(final ApplicationEvent event, @Nullable ResolvableType eventType) {
 * 		ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
 * 		Executor executor = getTaskExecutor();
 * 		for (ApplicationListener<?> listener : getApplicationListeners(event, type)) {
 * 			if (executor != null) {
 * 				executor.execute(() -> invokeListener(listener, event));
 *            }
 * 			else {
 * 				invokeListener(listener, event);
 *            }
 *        }
 *    }
 * ```
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 20:23
 * @since
 */
@Slf4j
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // 启动 springApplication 上下文
        // 初始化  SimpleApplicationEventMulticaster
        //  AbstractApplicationContext#initApplicationEventMulticaster
        applicationContext.refresh();

        ExecutorService executor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("async-thread-pool"));


        ApplicationEventMulticaster eventMulticaster =
                applicationContext.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (eventMulticaster instanceof SimpleApplicationEventMulticaster) {
            // 设置异步执行事件通知
            ((SimpleApplicationEventMulticaster) eventMulticaster).setTaskExecutor(executor);


            // 容器关闭时回调
            eventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!executor.isShutdown()) {
                        executor.shutdown();
                    }
                }
            });


            ((SimpleApplicationEventMulticaster) eventMulticaster).setErrorHandler(new ErrorHandler() {
                @Override
                public void handleError(Throwable t) {
                    t.printStackTrace();
                    log.error("spring 事件 异常时: error message =  [{}]", t.getMessage());
                }
            });
        }

        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                throw new RuntimeException("occur exception in event handler!");
            }
        });




        applicationContext.publishEvent(new CustomEvent1("applicationContext"));

        applicationContext.close();
    }
}
