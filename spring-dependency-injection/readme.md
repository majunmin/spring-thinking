# 依赖注入


## setter 方法注入

- XML 方式
- 注解方式
- Java Api方式


## 构造器注入


- api  `org.springframework.beans.factory.support.AbstractBeanDefinition#setAutowireMode` 设置构造器注入

官方为什么推荐使用  构造器注入?
构造器注入的bean注入顺序是可控的




## 属性注入

@Autowired
@Inject
@Resource


@Autowired 会忽略掉静态字段


## 方法注入

@Autowired
@Inject
@Resource
@Bean

@Autowired 会忽略掉静态方法


## 接口回调注入


ApplicationContextAware
EnvironmentAware
ResourceLoaderAware
BeanNameAware
BeanClassLoaderAware
BeanFactoryAware
MessageSourceAware               - 用于spring 国际化, 利用 resourceBounder
ApplicationEventPublisherAware   -  用于spring事件
EmbeddedValueResolverAware       - 获取springValueResolver,处理占位符


## 依赖处理的过程


`AutowireCapableBeanFactory.resolveDependency(DependencyDescriptor, String)`

依赖描述符: DependencyDescriptor
DependencyDescriptor


resolveMultipleBeans
![](https://gitee.com/niubenwsl/image_repo/raw/master/image/java/20210415234311.png)


CommonAnnotationBeanPostProcessor
AutowiredAnnotationBeanPostProcessor
接口继承关系
![](https://gitee.com/niubenwsl/image_repo/raw/master/image/java/CommonAnnotationBeanPostProcessor.png)





