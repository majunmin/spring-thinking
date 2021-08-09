package com.majm.service.impl;

import com.majm.dao.UserBalanceDao;
import com.majm.entity.UserBalance;
import com.majm.service.IsolationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-09 19:46
 * @since
 */
@Slf4j
@Service
public class IsolationServiceImpl implements IsolationService {

    @Autowired
    private UserBalanceDao userBalanceDao;


    @Override
    public void insert(UserBalance po) {
        String threadName = Thread.currentThread().getName();
        log.info(threadName + ": 添加用户信息: " + po);
        userBalanceDao.insert(po);
        log.info(threadName + "添加用户信息成功");
    }

    @Override
    public void delete(int id) {
        String threadName = Thread.currentThread().getName();
        log.info(threadName + ": 删除用户信息 id= {}", id);
        userBalanceDao.delete(id);
        log.info(threadName + ": 删除用户信息成功");
    }

    @Override
    public void update(UserBalance po) {
        String threadName = Thread.currentThread().getName();
        log.info(threadName + ": 更新用户信息 po= {}", po);
        userBalanceDao.update(po);
        log.info(threadName + ": 更新用户信息成功");
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void readUncommittedTest() {
        readUncommitted_insert(new UserBalance("uncommitted", 1000));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1 / 0);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class)
    protected void readUncommitted_insert(UserBalance po) {
        insert(po);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void getReadUncommittedAllList() {
        for (int i = 0; i < 3; i++) {
            log.info("[{}] 获取用户账户列表信息 start-----", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final List<UserBalance> beforeList = getAllList();
            beforeList.forEach(System.out::println);
            log.info("[{}] 获取用户账户列表信息 end-----", Thread.currentThread().getName());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void readCommittedTest() {
        readCommitted_insert(new UserBalance("readComitted", 1000));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    protected void readCommitted_insert(UserBalance po) {
        insert(po);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void getReadCommittedAllList() {
        for (int i = 0; i < 3; i++) {
            try {
                log.info("[{}] 获取用户账户信息列表start=========", Thread.currentThread().getName());
                userBalanceDao.getAllList().forEach(System.out::println);
                TimeUnit.SECONDS.sleep(3);
                log.info("[{}] 获取用户账户信息列表end=========", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void repeatableReadTest() {
        repeatable_insert(new UserBalance("repeatable", 3000));
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatable_insert(UserBalance userBalance) {
        insert(userBalance);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void getRepeatableAllList() {
        for (int i = 0; i < 3; i++) {
            try {
                log.info("[{}] 获取用户账户信息列表start=========", Thread.currentThread().getName());
                userBalanceDao.getAllList().forEach(System.out::println);
                TimeUnit.SECONDS.sleep(3);
                log.info("[{}] 获取用户账户信息列表end=========", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void serializableTest() {

    }

    @Override
    public List<UserBalance> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        userBalanceDao.rechargeAmount(toUserId, amount);
        userBalanceDao.deductionAmount(fromUserId, amount);
    }
}
