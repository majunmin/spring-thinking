package com.majm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 余额类 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 21:31
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {

    private Long id;

    private String userName;

    private double balance;

    public UserBalance(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }
}
