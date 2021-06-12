package com.majm.aop;

import java.time.Duration;
import java.time.Instant;

/**
 * 静态代理模式  or  装饰器模式 </br>
 * 装饰器模式:  对功能的增强
 * 代理模式:    增加额外的功能
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 00:52
 * @since
 */
public class ProxyEchoService implements EchoService {

    private EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        Instant startTime = Instant.now();
        echoService.echo(message);
        System.out.println("echo() execute cost(ms):  " + Duration.between(startTime, Instant.now()).toMillis());
        return message;
    }
}
