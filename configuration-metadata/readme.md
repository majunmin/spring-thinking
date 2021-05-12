# Spring 配置元信息

- Xml资源 `XmlBeanDefinitionReader`
- Properties资源 `PropertiesBeanDefinitionReader`
- 注解资源 `AnnotatedBeanDefinitionReader`




## #Import

```java


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Import {

    /**
     * {@link Configuration @Configuration}, {@link ImportSelector},
     * {@link ImportBeanDefinitionRegistrar}, or regular component classes to import.
     */
    Class<?>[] value();

}
```


## spring xml Extensible  XML authoring

1. xsd 文件         :  XML schema
2. spring.schema    : schema绝对路径和相对路径的一个映射关系
3. spring.handlers  : 配置 命名空间和 NamespaceHandler 的映射关系 




## PropertySources

里面维护一个  CopyOnWriteArrayList  的 PropertySource 列表, 前面的优先级高于后面的优先级



## spring 内建实现  PropertySource

| PropertySource | desc |
| ---- | ---- |
|org.springframework.core.env.CommandLinePropertySource | 命令行配置属性源 |
|org.springframework.jndi.JndiPropertySource            | jndi配置属性源 |
|org.springframework.core.env.PropertiesPropertySource  | Prooperties配置属性源|
|org.springframework.web.context.support.ServletConfigPropertySource    | 单个 Servlet配置 |
|org.springframework.web.context.support.ServletContextPropertySource   | ServletContext 配置|
|org.springframework.core.env.SystemEnvironmentPropertySource   |    环境变量属性配置 |


`CompositePropertySource`: 组合 `Set<PropertySource<?>>`, 提供统一操作(组合模式), 组合多个向具有相同 name 的 PropertySource.



## 基于 注解 或 API 扩展 spring配置属性源



1. @PropertySource



2. Spring 应用上下文启动前装配 PropertySource



3. Spring 应用上下文启动后装配 PropertySource

