package com.majm.spring.annotation.server;

/**
 * 定义服务器接口 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 17:05
 * @since
 */
public interface Server {

    /**
     * 启动服务器
     */
    void start();

    /**
     * 关闭服务器
     */
    void stop();


    /**
     * serve 服务类型
     */
    enum Type {
        HTTP, FTP
    }
}
