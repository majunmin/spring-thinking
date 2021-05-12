package com.majm.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * HelloWorld 模块 {@link ImportSelector}实现 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-08 23:18
 * @since
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getCanonicalName()};
    }
}
