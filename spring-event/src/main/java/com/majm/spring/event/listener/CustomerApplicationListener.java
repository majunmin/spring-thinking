package com.majm.spring.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * {@link ApplicationListener} 事件监听器实现 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 20:14
 * @since
 */
@Slf4j
public class CustomerApplicationListener implements ApplicationListener<CustomEvent1> {
    @Override
    public void onApplicationEvent(CustomEvent1 event) {
        // business process
        log.info("CustomerApplicationListener#onApplicationEvent() eventName = [{}]", event.getSource());
    }
}
