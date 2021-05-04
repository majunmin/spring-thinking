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

