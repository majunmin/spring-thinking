package com.majm.aop.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件  </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-07-18 17:28
 * @since
 */
public class ExecuteEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ExecuteEvent(Object source) {
        super(source);
    }
}
