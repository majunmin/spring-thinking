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

## ComponentScan 解析步骤

`ClassPathBeanDefinitionScanner`: 扫描 classPath 路径下 的类并解析为 BeanDefinition `ClassPathBeanDefinitionScanner.doScan()`
`ComponentScanAnnotationParser`: ComponentScan 注解解析 类吗内部 主要是 调用  `ClassPathBeanDefinitionScanner.doScan()`

@ComponentScan 注解的解析开始是在

`ConfigurationClassParser.doProcessConfigurationClass`

## Spring 组合注解

`@TransactionalService`

## Enable注解模块驱动

> @Enable 注解模块驱动为前缀的的注解驱动编程模型,所谓模块是指具备相同领域功能组件的集合.
> 比如 webMvc模块, AspectJ 模块, Caching模块, Async 模块, JMX(java管理扩展)模块

@EnableWebMvc @EnableTransactionManagement @EnableCaching @EnableMBeanExport @EnableAsync

## Spring 条件注解

1. 基于配置条件注解 `org.springframework.context.annotation.Profile`
  - 关联对象: `org.springframework.core.env.Environment` 中的 profile (`Environment.getActiveProfiles`)
  - 实现变化: 从spring4.0 开始 , `@Profile` 注解的实现 基于 `@Conditional` 实现

2. 基于编程的条件注解: `org.springframework.context.annotation.Conditional`
  - 关联对象:  `org.springframework.context.annotation.Condition`


Conditional实现原理

- 上下文对象 `org.springframework.context.annotation.ConditionContext`
- 条件判断   `org.springframework.context.annotation.ConditionEvaluator`
- 配置阶段   `org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase`
- 判断入口   `org.springframework.context.annotation.ConfigurationClassPostProcessor` 
  * `org.springframework.context.annotation.ConfigurationClassParser`
    

### ConditionalOnMissingBean  ConditionalOnBean

> 根据当前环境或者容器情况来动态注入bean, 要配合 `@Bean` 使用
> @ConditionalOnMissingBean: 判断当前需要注入Spring容器(BeanFactory)中的bean的实现类是否已经含有，有的话不注入，没有就注入
> @ConditionalOnBean:        判断当前需要注册的bean的实现类否被spring管理,如果被管理则注入,反之不注入






----------------------------------------
[@ConditionalOnMissingBean与@ConditionalOnBean用法](https://blog.csdn.net/weixin_44792004/article/details/103661167)

