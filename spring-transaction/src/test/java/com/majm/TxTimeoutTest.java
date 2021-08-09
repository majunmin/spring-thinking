package com.majm;

import com.majm.service.TxTimeoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring事务超时机制 </br>
 * <p>
 * <p>
 * {@link org.springframework.transaction.annotation.Transactional}注解配置了 timeout属性 和 去掉 timeout属性 事务超时是否回滚
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 23:54
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-context.xml"})
public class TxTimeoutTest {

    @Autowired
    public TxTimeoutService timeoutService;

    @Test
    public void testTxTimeout() {
        timeoutService.timeoutTransaction();
    }
}
