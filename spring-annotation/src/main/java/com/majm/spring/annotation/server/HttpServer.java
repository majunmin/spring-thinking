package com.majm.spring.annotation.server;


import lombok.extern.slf4j.Slf4j;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 23:02
 * @since
 */
@Slf4j
public class HttpServer implements Server {


    @Override
    public void start() {
        log.info("http server starting...");
    }

    @Override
    public void stop() {
        log.info("http stoping...");
    }
}
