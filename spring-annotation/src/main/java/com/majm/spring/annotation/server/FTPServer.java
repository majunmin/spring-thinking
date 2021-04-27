package com.majm.spring.annotation.server;

import lombok.extern.slf4j.Slf4j;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 23:04
 * @since
 */
@Slf4j
public class FTPServer implements Server {
    @Override
    public void start() {
        log.info("ftp server starting...");
    }

    @Override
    public void stop() {
        log.info("ftp server stoping...");
    }
}
