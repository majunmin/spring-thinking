# Spring Environment 抽象

## Spring 占位符处理

- spring3.1 前的处理
    * 组件: `org.springframework.beans.factory.config.PropertyPlaceholderConfigurer`
    * 接口:`org.springframework.util.StringValueResolver`
- spring3.1之后的处理
    * 组件: `org.springframework.context.support.PropertySourcesPlaceholderConfigurer`
    * 接口: `org.springframework.beans.factory.config.EmbeddedValueResolver`

## Spring 类型转换 在 Environment 中的使用

- Environment 底层实现
    * 底层实现 `org.springframework.core.env.PropertySourcesPropertyResolver`
        * 核心方法  `convertValueIfNecessary`
    * 底层服务 `org.springframework.core.convert.ConversionService`
        * 默认实现 `org.springframework.core.convert.support.DefaultConversionService`

## Spring 额理性转换在@Value 中的使用

1. @Value 底层实现

- 底层实现 `org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor`
    * `org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency`
- 底层服务 `org.springframework.beans.TypeConverter`
    * `org.springframework.beans.TypeConverterDelegate`
    * `org.springframework.core.convert.ConversionService`


