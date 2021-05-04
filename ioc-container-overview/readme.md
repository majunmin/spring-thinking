## spring 容器概览




### 依赖注入来源


1. 配置元数据  xml
2. 注解配置 @Bean
3. api 方式 BeanDefinitionBuilder
4. 可处理依赖|游离对象|非spring容器管理对象
```java
// BeanFactory interface not registered as resolvable type in a plain factory.
// MessageSource registered (and found for autowiring) as a bean.
beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
beanFactory.registerResolvableDependency(ResourceLoader.class, this);
beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
beanFactory.registerResolvableDependency(ApplicationContext.class, this);
```










































