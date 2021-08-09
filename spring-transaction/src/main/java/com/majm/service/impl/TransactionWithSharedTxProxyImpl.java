package com.majm.service.impl;

import com.majm.dao.UserBalanceDao;
import com.majm.entity.UserBalance;
import com.majm.service.UserBalanceService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 22:29
 * @since
 */
@Component("transactionWithSharedTxProxy")
public class TransactionWithSharedTxProxyImpl implements UserBalanceService {

    private UserBalanceDao userBalanceDao;

    @Override
    public List<UserBalance> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        System.out.println("所有Bean共享同一个代理事务-转账开始");
        System.out.println(String.format("用户ID：%s 给用户ID：%s 转账金额：%s，是否模拟异常：%s", fromUserId, toUserId, amount, needException));
        userBalanceDao.deductionAmount(fromUserId, amount);
        if (needException) {
            throw new RuntimeException("所有Bean共享同一个代理事务-转账异常");
        }
        userBalanceDao.rechargeAmount(toUserId, amount);
        System.out.println("所有Bean共享同一个代理事务-转账成功！");
    }


}
