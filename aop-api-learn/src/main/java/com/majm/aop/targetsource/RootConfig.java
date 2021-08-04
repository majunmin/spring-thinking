package com.majm.aop.targetsource;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.framework.autoproxy.target.LazyInitTargetSourceCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-07-06 22:02
 * @since
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RootConfig {

    @Bean(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME)
    public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator() {
        AnnotationAwareAspectJAutoProxyCreator autoProxyCreator = new AnnotationAwareAspectJAutoProxyCreator();
        // 手动设置 TargetsourceCreator, 可以设置多个,责任链模式
        autoProxyCreator.setCustomTargetSourceCreators(new LazyInitTargetSourceCreator());
        return autoProxyCreator;
    }

}


