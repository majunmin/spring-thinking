package com.majm.spring.event.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-30 19:36
 * @since
 */
@Slf4j
public class MyService {

    @Async
    public void testMethod(){
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread: [{}], MyService#testMethod() execute() ...", Thread.currentThread().getName());
        }
    }
}
