package com.majm;

import com.majm.entity.UserBalance;
import com.majm.service.IsolationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://www.cnblogs.com/fengzheng/p/12557762.html
 * 事务隔离级别测试 多线程模拟并发操作 </br>
 * <p>
 * 1. ReadUncommitted
 * 2. ReadCommitted
 * 3. RepeatableRead
 * 4. Serializable
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-09 19:42
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-context.xml"})
public class IsolationTest {

    private ThreadPoolExecutor threadPool;

    @Autowired
    private IsolationService isolationService;

    @Before
    public void init() {
        threadPool = new ThreadPoolExecutor(5, 5, 2000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(500), new CustomizableThreadFactory("thread-pool-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Test
    public void testReadUncomitted() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        threadPool.submit(() -> {
            try {
                isolationService.readUncommittedTest();
            } finally {
                latch.countDown();
            }
        });
        threadPool.submit(() -> {
            try {
                isolationService.getReadUncommittedAllList();
            } finally {
                latch.countDown();
            }
        });

        latch.await();

        System.out.println("事务结束后,再次读取");
        for (UserBalance userBalance : isolationService.getAllList()) {
            System.out.println(userBalance);
        }
    }

    /**
     * 1. org.springframework.transaction.interceptor.TransactionInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     * 2. org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction(java.lang.reflect.Method, java.lang.Class, org.springframework.transaction.interceptor.TransactionAspectSupport.InvocationCallback)
     * 3. org.springframework.transaction.support.AbstractPlatformTransactionManager#getTransaction(org.springframework.transaction.TransactionDefinition)
     * 4. org.springframework.transaction.support.AbstractPlatformTransactionManager#startTransaction(org.springframework.transaction.TransactionDefinition, java.lang.Object, boolean, org.springframework.transaction.support.AbstractPlatformTransactionManager.SuspendedResourcesHolder)
     * 5. org.springframework.jdbc.datasource.DataSourceTransactionManager#doBegin(java.lang.Object, org.springframework.transaction.TransactionDefinition)
     * 6. org.springframework.jdbc.datasource.DataSourceUtils#prepareConnectionForTransaction(java.sql.Connection, org.springframework.transaction.TransactionDefinition)
     *
     * @throws InterruptedException
     */
    @Test
    public void testReadCommitted() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        threadPool.submit(() -> {
            isolationService.readCommittedTest();
            latch.countDown();
        });

        threadPool.submit(() -> {
            isolationService.getReadCommittedAllList();
            latch.countDown();
        });

        latch.await();

        System.out.println("事务结束后,再次读取");
        for (UserBalance userBalance : isolationService.getAllList()) {
            System.out.println(userBalance);
        }
    }

    @Test
    public void testRepeatableRead() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        threadPool.submit(() -> {
            isolationService.repeatableReadTest();
            latch.countDown();
        });

        threadPool.submit(() -> {
            isolationService.getRepeatableAllList();
            latch.countDown();
        });
        latch.await();

        System.out.println("事务结束后,再次读取");
        for (UserBalance userBalance : isolationService.getAllList()) {
            System.out.println(userBalance);
        }

    }

    /**
     * 相当于将  事务的执行 变为顺序执行,隔离效果最好,但是  性能最差,相当于单线程执行，后一个事务必须等待前一个事务执行完成
     */
    public void testSerializable() {

    }

    @After
    public void close() {
        threadPool.shutdown();
    }
}
