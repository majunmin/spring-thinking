package com.majm.spring.annotation.server;

import com.majm.spring.annotation.EnableHelloWorld;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link EnableHelloWorld}  Enable 模块模式 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 17:05
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ServerImportSelector.class)
public @interface EnableServer {

    // 设置服务器类型
    Server.Type type() default Server.Type.HTTP;
}
