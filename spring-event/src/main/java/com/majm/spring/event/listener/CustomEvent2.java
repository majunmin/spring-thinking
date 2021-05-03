package com.majm.spring.event.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 20:17
 * @since
 */
public class CustomEvent2 extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public CustomEvent2(Object source) {
        super(source);
    }
}
