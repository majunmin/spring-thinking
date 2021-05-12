package com.majm.spring.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义应用注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyConfiguration(name= "my-application")
public @interface MyApplication {
}
