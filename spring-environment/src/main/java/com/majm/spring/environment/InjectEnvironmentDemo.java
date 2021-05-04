package com.majm.spring.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Injection {@link org.springframework.core.env.Environment} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 13:07
 * @since
 */
@Slf4j
public class InjectEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext autowiredContext;

    private Environment environment;
    private ApplicationContext context;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectEnvironmentDemo.class);
        applicationContext.refresh();

        InjectEnvironmentDemo demo = applicationContext.getBean(InjectEnvironmentDemo.class);
        Environment lookUpEnv = applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);


        log.info("lookUpEnv == awareEnv: {}", lookUpEnv == demo.environment);
        log.info("autowiredEnv == awareEnv: {}", demo.env == demo.environment);
        log.info("autowiredEnv == awareEnv: {}", demo.env == demo.environment);
        log.info("awareEnv == applicationContext.getEnvironment() : {}", demo.env == applicationContext.getEnvironment());


        log.info("autowiredContext == applicationContext : {}", demo.autowiredContext == applicationContext);
        log.info("awareContext == applicationContext : {}", demo.context == applicationContext);


        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
