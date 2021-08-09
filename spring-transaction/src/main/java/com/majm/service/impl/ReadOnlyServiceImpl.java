package com.majm.service.impl;

import com.majm.dao.UserBalanceDao;
import com.majm.entity.UserBalance;
import com.majm.service.ReadOnlyService;
import com.majm.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 23:40
 * @since
 */
@Service
public class ReadOnlyServiceImpl implements ReadOnlyService {

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Override
    public List<UserBalance> getAllList() {
        return userBalanceDao.getAllList();
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class, readOnly = true)
    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表 start =============");
        final List<UserBalance> beforeList = userBalanceDao.getAllList();
        beforeList.forEach(System.out::println);
        System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表 end =============");
        userBalanceDao.deductionAmount(fromUserId, amount);
        userBalanceDao.rechargeAmount(toUserId, amount);
    }
}
