package com.majm.aop.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-11 13:40
 * @since
 */
public class CglibClient {

    public static void main(String[] args) {
        // 将代理类 持久化到本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./classes/");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        // 设置拦截对象
        enhancer.setCallbacks(new Callback[]{new DaoProxy(), new DaoAnotherProxy(), NoOp.INSTANCE});
        enhancer.setCallbackFilter(new DaoFilter());


        // 生成代理类 并返回
        Dao dao = (Dao)enhancer.create();

        //select优先级高 使用DaoProxy
        dao.select();
        dao.insert();
        dao.delete();
    }
}
