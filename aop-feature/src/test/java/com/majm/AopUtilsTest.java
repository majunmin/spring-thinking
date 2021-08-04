package com.majm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Aop工具类测试 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-03 08:04
 * @since
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AopConfig.class)
public class AopUtilsTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testIsAop() {
        AopUtils.isAopProxy(employeeService);
    }

    @Test
    public void testAopProxyUtils() {
        AopProxyUtils.ultimateTargetClass(employeeService);
    }

}
