package com.majm.spring.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 12:56
 * @since
 */
@Slf4j
public class LookUpEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LookUpEnvironmentDemo.class);

        applicationContext.refresh();

        LookUpEnvironmentDemo lookUpEnvironmentDemo = applicationContext.getBean(LookUpEnvironmentDemo.class);
        Environment environment = lookUpEnvironmentDemo.environment;

        log.info("{}", environment);

        // StandardEnvironment
        Environment env = applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        log.info("{}", env);

        log.info("EnvironmentAware.env == lookUpEnv : {}", env == environment);

        applicationContext.close();
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
