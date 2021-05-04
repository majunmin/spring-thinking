package com.majm.bean.scope;

import com.majm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 16:15
 * @since
 */
public class ThreadLocalScopeDemo {


    @Autowired
    private User user;

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return new User();
    }


    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();

        scopedBeanByLookUp(applicationContext);



        applicationContext.close();
    }

    private static void scopedBeanByLookUp(AnnotationConfigApplicationContext applicationContext) throws InterruptedException {
        IntStream.rangeClosed(1, 3).mapToObj(i -> (Runnable)() ->{
            User user = applicationContext.getBean(User.class);
            System.out.println(user);
        }).map(Thread::new).forEach(Thread::start);

    }
}
