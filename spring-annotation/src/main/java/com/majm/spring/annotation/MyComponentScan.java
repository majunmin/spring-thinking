package com.majm.spring.annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 {@link org.springframework.stereotype.Component}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan
public @interface MyComponentScan {

    // scanBasePackages
    //    -> @AliasFor  @ComponentScan.basePackages -> @ComponentScan.value(显性别名)
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages") // 隐形别名  子注解定义新的属性  引用父注解的 属性方法
    String[] scanBasePackages() default {"#"};
}
