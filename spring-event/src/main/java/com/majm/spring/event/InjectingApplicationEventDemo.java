package com.majm.spring.event;

import com.majm.spring.event.listener.CustomEvent1;
import com.majm.spring.event.listener.CustomerApplicationListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * inject {@link ApplicationEventPublisher} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-30 13:12
 * @since
 */
public class InjectingApplicationEventDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    private ApplicationContext context;

    private ApplicationEventPublisher applicationEventPublisher;

    // #3
    @PostConstruct
    public void init() {
        applicationEventPublisher.publishEvent(new CustomEvent1("@PostConstruct - applicationEventPublisher.publishEvent()"));
        context.publishEvent(new CustomEvent1("@PostConstruct - ApplicationContext.publishEvent()"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingApplicationEventDemo.class);

        applicationContext.addApplicationListener(new CustomerApplicationListener());

        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext; // #2
        applicationEventPublisher.publishEvent(new CustomEvent1("ApplicationContextAware#setApplicationContext(): publish event"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher; //#1
        applicationEventPublisher.publishEvent(new CustomEvent1("ApplicationEventPublisherAware#setApplicationEventPublisher(): publish event"));
    }
}
