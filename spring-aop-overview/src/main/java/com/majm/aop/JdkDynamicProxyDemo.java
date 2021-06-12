package com.majm.aop;

import com.majm.aop.interceptor.AfterReturnInterceptor;
import com.majm.aop.interceptor.BeforeInterceptor;
import com.majm.aop.interceptor.ExceptionInterceptor;
import com.majm.aop.interceptor.FinallyInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;

/**
 * JDK 动态代理 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 00:57
 * @since
 */
public class JdkDynamicProxyDemo {


    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // common proxy
//        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Object result = null;
//                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
//                    Instant start = Instant.now();
//                    try {
//                        result = method.invoke(new DefaultEchoService(), args[0]);
//                    } finally {
//                        Instant end = Instant.now();
//                        System.out.println("method execute cost[ms] :" + Duration.between(start, end).toMillis());
//
//                    }
//                }
//                return result;
//            }
//        });

        // proxy extends  Proxy implements  EchoService
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                Long start = 0L;
                Long end = 0L;
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // before execute
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return Instant.now().toEpochMilli();
                        }
                    };
                    start = (Long) beforeInterceptor.before(proxy, method, args);
                    try {
                        result = method.invoke(new DefaultEchoService(), args[0]);
                        AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
                            @Override
                            public Object after(Object proxy, Method method, Object[] args, Object result) {
                                return Instant.now().toEpochMilli();
                            }
                        };
                        // after return execute
                        end = (Long) afterReturnInterceptor.after(proxy, method, args, result);
                    } catch (Exception ex) {

                        //execute when exception
                        ExceptionInterceptor exceptionInterceptor = new ExceptionInterceptor() {
                            @Override
                            public Object exception(Object proxy, Method method, Object[] args) {
                                System.out.println(ex);
                                return ex;
                            }
                        };
                        exceptionInterceptor.exception(proxy, method, args);

                    } finally {
                        Long finalStart = start;
                        Long finalEnd = end;

                        // finally 执行
                        FinallyInterceptor finallyInterceptor = new FinallyInterceptor() {
                            @Override
                            public void finalize(Object proxy, Method method, Object[] args) {
                                System.out.println("method execute cost : " + (finalEnd - finalStart));
                            }
                        };
                        finallyInterceptor.finalize(proxy, method, args);
                    }
                }
                return result;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello world!");


        Object proxy2 = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Comparable.class}, (proxy1, method, args1) -> null);
        System.out.println(proxy2);

        Object proxy3 = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{Comparable.class}, (proxy1, method, args1) -> null);
        System.out.println(proxy3);
    }
}
