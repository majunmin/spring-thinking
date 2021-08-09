package com.majm.service;

import com.majm.entity.UserBalance;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-09 19:46
 * @since
 */
public interface IsolationService extends UserBalanceService {

    /**
     * 插入一条记录
     *
     * @param po
     */
    void insert(UserBalance po);

    /**
     * 删除记录
     *
     * @param id
     */
    void delete(int id);

    /**
     * 更新记录
     *
     * @param po
     */
    void update(UserBalance po);


    /**
     * 隔离级别-读未提交测试
     */
    void readUncommittedTest();

    /**
     * 获取读未提交隔离级别下的用户账户列表
     */
    void getReadUncommittedAllList();

    /**
     * 隔离级别-读提交测试
     */
    void readCommittedTest();

    /**
     * 获取读提交隔离级别下的用户账户列表
     */
    void getReadCommittedAllList();

    /**
     * 隔离级别-可重复读测试
     */
    void repeatableReadTest();

    /**
     * 获取可重复读隔离级别下的用户账户列表
     */
    void getRepeatableAllList();

    /**
     * 隔离级别-串行化测试
     */
    void serializableTest();

    /**
     * 获取串行化隔离级别下的用户账户列表
     */
}
