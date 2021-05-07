# ConfigurationClassPostProcessor

> ConfigurationClassPostProcessor 实现了 BeanDefinitionRegistryPostProcessor 接口  (BeanFactoryPostProcessor)
>
>
>
>

## ConfigurationClassPostProcessor#postProcessBeanDefinition()

1. 筛选出 @Configuration 注解的 配置类
2. 按照 @Order 接口排序
3. `ConfigurationClassParser#parse()`   解析每一个 @ConfigurationClass 配置类
    - `ConfigurationClassParser#processConfigurationClass`
    - `ConfigurationClassParser#doProcessConfigurationClass()`
        * @Component
        * @PropertySource
        * @ComponentScan
        * @Import
        * @ImportResourOCOnfiugce
        * @Bean

4. 将解析出来的 Bean 解析为 BeanDefinition,并注册进  `BeanDefinitionRegistry` (@Import @Bean @ImportResource @PropertySource
   @ComponentScan...)
5. 再次获取一下 BeanDefinitionRegistry中BeanDefinition 的数量, 如果数量有增加, 说明有新增的 BeanDefinition进来, 找出 新增的 BeanDefinition , 继续步骤3

## spring 注解派生性原理

- @Component 派生性原理

    * 核心组件
        - org.springframework.context.annotation.ClassPathBeanDefinitionScanner
        - org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
    * 资源处理
        - org.springframework.core.io.support.ResourcePatternResolver
    * 资源 类元信息
        - org.springframework.core.type.classreading.MetadataReaderFactory
    * 类元信息
        - org.hibernate.validator.internal.metadata.aggregated.ClassMetaData
        - ASM: org.springframework.core.type.classreading.ClassMetadataReadingVisitor
        - 反射实现: org.springframework.core.type.StandardAnnotationMetadata
    * 注解元信息
        - org.springframework.core.type.AnnotationMetadata
        - ASM: org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor
        - 反射实现: org.springframework.core.type.StandardAnnotationMetadata
