package com.majm.spring.metadata;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * {@link AnnotationMetadata} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-26 20:37
 * @since
 */
@Configuration
public class AnnotationMetaDataDemo {

    public static void main(String[] args) {
        // StandardAnnotationMetadata 基于java反射
        AnnotationMetadata metadata = AnnotationMetadata.introspect(AnnotationMetaDataDemo.class);
        Set<String> annotationTypes = metadata.getAnnotationTypes();
        annotationTypes.forEach(System.out::println);


        // 访问者模式
        // SimpleMetadataReader ASM方式
        MetadataReader metadataReader;
        try {
            metadataReader = new SimpleMetadataReaderFactory().getMetadataReader(AnnotationMetaDataDemo.class.getCanonicalName());
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            annotationMetadata.getAnnotationTypes().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
