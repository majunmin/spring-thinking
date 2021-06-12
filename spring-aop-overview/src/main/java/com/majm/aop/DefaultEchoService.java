package com.majm.aop;

/**
 * EchoService 实现类 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 00:51
 * @since
 */
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) throws NullPointerException {
        System.out.println("[ECHO] : " + message);
        return message;
    }
}
