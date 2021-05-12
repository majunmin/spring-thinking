package com.majm.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 偶数Profile 条件 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-09 00:35
 * @since
 */
public class EvenCondition implements Condition {

    /**
     * 条件上下文
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.acceptsProfiles("even");
    }
}
