package com.majm.service;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 22:06
 * @since
 */
public interface RollBackService {

    void defaultRollback();

    void rollbackFor1() throws InterruptedException;

    void rollbackFor2() throws InterruptedException;

    void rollbackFor3() throws InterruptedException;

    void nonRollback1() throws InterruptedException;

    void nonRollback2() throws InterruptedException;


}
