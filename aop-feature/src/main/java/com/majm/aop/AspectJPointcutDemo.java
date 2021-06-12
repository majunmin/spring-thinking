package com.majm.aop;

import com.majm.aop.config.AspectJConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Pointcut demo </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-12 10:45
 * @since
 */
@EnableAspectJAutoProxy
public class AspectJPointcutDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJPointcutDemo.class);
        context.register(AspectJConfig.class);
        context.refresh();
        AspectJPointcutDemo demo = context.getBean(AspectJPointcutDemo.class);

        demo.execute();

        context.close();
    }

    public void execute() {
        System.out.println("execute ....");
    }
}
