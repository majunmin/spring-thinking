package com.majm.aop;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 00:50
 * @since
 */
public class StaticProxy {

    public static void main(String[] args) {
        ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello world!");
    }

}
