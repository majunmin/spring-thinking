package com.majm.dao.impl;

import com.majm.dao.UserBalanceDao;
import com.majm.entity.UserBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 22:08
 * @since
 */
@Repository
public class UserBalanceDaoImpl implements UserBalanceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public List<UserBalance> getAllList() {
        return getJdbcTemplate().query("select id,user_name,balance from user_balance;", new RowMapper<UserBalance>() {
            @Override
            public UserBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
                final UserBalance userBalance = new UserBalance();
                userBalance.setId(rs.getLong("id"));
                userBalance.setUserName(rs.getString("user_name"));
                userBalance.setBalance(rs.getDouble("balance"));
                return userBalance;
            }
        });
    }

    @Override
    public void rechargeAmount(int id, double amount) {
        getJdbcTemplate().update("update user_balance set balance = balance + ? where id = ?",
                amount, id);
    }

    @Override
    public void deductionAmount(int id, double amount) {
        getJdbcTemplate().update("update user_balance  set balance  balance - ? where id  = ?",
                amount, id);
    }

    @Override
    public void insert(UserBalance po) {
        getJdbcTemplate().update("insert into user_balance (user_name, balance) values(?, ?)",
                po.getUserName(), po.getBalance());
    }

    @Override
    public void delete(int id) {
        getJdbcTemplate().update("delete from user_balance where id = ?", id);
    }

    @Override
    public void update(UserBalance po) {
        getJdbcTemplate().update("update user_balance set user_name = ?, balance = ? where id = ?",
                po.getUserName(), po.getBalance(), po.getId());
    }
}
