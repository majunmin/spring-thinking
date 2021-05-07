package com.majm.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 层次性事件传播示例 </br>
 * <p>
 * 子应用上下文发布的事件 父应用上下文可以监听到
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-03 10:54
 * @since
 */
@Slf4j
public class HierachicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyApplicationListener.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.setParent(parentContext);
        currentContext.register(MyApplicationListener.class);

        // 启动应用上下文
        parentContext.refresh();
        currentContext.refresh();


    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            log.info("监听到 spring应用上下文 [ID : {}] 接收到事件 ContextRefreshedEvent", event.getApplicationContext().getId());
        }
    }
}
