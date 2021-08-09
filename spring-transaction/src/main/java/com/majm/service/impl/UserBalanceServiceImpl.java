package com.majm.service.impl;

import com.majm.entity.UserBalance;
import com.majm.service.UserBalanceService;

import java.util.List;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 21:58
 * @since
 */
public class UserBalanceServiceImpl implements UserBalanceService {

    @Override
    public List<UserBalance> getAllList() {
        return null;
    }

    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {

    }
}
