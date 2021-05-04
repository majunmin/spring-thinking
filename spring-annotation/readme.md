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
        * @ImportResource
        * @Bean

4. 将解析出来的 Bean 解析为 BeanDefinition,并注册进  `BeanDefinitionRegistry` (@Import @Bean @ImportResource @PropertySource
   @ComponentScan...)
5. 再次获取一下 BeanDefinitionRegistry中BeanDefinition 的数量, 如果数量有增加, 说明有新增的 BeanDefinition进来, 找出 新增的 BeanDefinition , 继续步骤3
6. 