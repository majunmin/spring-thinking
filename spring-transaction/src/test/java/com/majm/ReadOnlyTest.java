package com.majm;

import com.majm.service.ReadOnlyService;
import com.majm.service.RollBackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring事务是否只读 </br>
 * <p>
 * 如果事务设置为只读(readonly = true), 那么更新数据将会抛出异常
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-08 23:45
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-context.xml"})
public class ReadOnlyTest {

    @Autowired
    private ReadOnlyService readOnlyService;

    @Test
    public void testReadOnly() {
        readOnlyService.transferAmount(1, 2, 50, false);
    }
}
