package com.majm.spring.event.listener;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 20:11
 * @since
 */
@Slf4j
public class CustomEvent1 extends ApplicationEvent {

    @Getter
    @Setter
    private Object data;

    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public CustomEvent1(String source) {
        super(source);
        log.info("process custom event 1: msg = [{}], data = [{}]", source, data);
    }
}
