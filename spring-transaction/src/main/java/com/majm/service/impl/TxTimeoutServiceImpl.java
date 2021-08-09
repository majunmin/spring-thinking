package com.majm.service.impl;

import com.majm.dao.UserBalanceDao;
import com.majm.entity.UserBalance;
import com.majm.service.TxTimeoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 23:50
 * @since
 */
@Service
public class TxTimeoutServiceImpl implements TxTimeoutService {

    @Autowired
    private UserBalanceDao userBalanceDao;


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class, timeout = 60)
    @Override
    public void timeoutTransaction() {
        userBalanceDao.insert(new UserBalance("timeout1", 1000.0d));
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userBalanceDao.insert(new UserBalance("timeout2", 1000.0d));
    }
}
