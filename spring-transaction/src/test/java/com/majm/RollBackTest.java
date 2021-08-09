package com.majm;

import com.majm.service.RollBackService;
import com.majm.service.UserBalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 事务回滚机制 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 22:02
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-context.xml"})
public class RollBackTest {

    @Autowired
    private RollBackService rollBackService;

    @Test
    public void rollBackTest() {
        rollBackService.defaultRollback();
    }

    @Test
    public void rollBackTest1() throws InterruptedException {
        rollBackService.rollbackFor1();
    }

    @Test
    public void rollBackTest2() throws InterruptedException {
        rollBackService.rollbackFor2();
    }

    @Test
    public void rollBackTest3() throws InterruptedException {
        rollBackService.rollbackFor3();
    }

    @Test
    public void nonRollBackTest1() throws InterruptedException {
        rollBackService.nonRollback1();
    }

    @Test
    public void nonRollBackTest2() throws InterruptedException {
        rollBackService.nonRollback2();
    }
}
