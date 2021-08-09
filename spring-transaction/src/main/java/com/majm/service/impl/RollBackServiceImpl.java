package com.majm.service.impl;

import com.majm.dao.UserBalanceDao;
import com.majm.entity.UserBalance;
import com.majm.service.RollBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 22:07
 * @since
 */
@Service
public class RollBackServiceImpl implements RollBackService {

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void defaultRollback() {
        userBalanceDao.insert(new UserBalance(7L, "defaultRollBack", 0));
        int[] nums = new int[3];
        System.out.println(nums[4]);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = ClassNotFoundException.class)
    @Override
    public void rollbackFor1() throws InterruptedException {
        userBalanceDao.insert(new UserBalance(7L, "defaultRollBack", 0));
        throw new InterruptedException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = InterruptedException.class)
    @Override
    public void rollbackFor2() throws InterruptedException {
        userBalanceDao.insert(new UserBalance(7L, "defaultRollBack", 0));
        throw new InterruptedException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = "java.lang.IOException")
    @Override
    public void rollbackFor3() throws InterruptedException {

    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, noRollbackFor = InterruptedException.class)
    @Override
    public void nonRollback1() throws InterruptedException {
        userBalanceDao.insert(new UserBalance(1L, "nonRollback1", 1));
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, noRollbackForClassName = "java.io.RuntimeException")
    @Override
    public void nonRollback2() throws InterruptedException {
        userBalanceDao.insert(new UserBalance(1L, "nonRollback2", 1));
        throw new InterruptedException();
    }
}
