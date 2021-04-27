# 依赖注入来源


## `org.springframework.beans.factory.support.BeanDefinitionRegistry`

BeanDefinition注册中心, 提供了 对 BeanDefinition 的管理操作
BeanDefinitionRegistry 的一个实现类是 `org.springframework.beans.factory.support.DefaultListableBeanFactory`

## `org.springframework.beans.factory.config.SingletonBeanRegistry`


他的一个实现类是 `org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#registeredSingletons`
SingletonBeanRegistry 单例对象注册中心,
单例对象没有被spring IOC容器管理, 但是可以被依赖查找,依赖注入找到
单例对象 的生命周期 没有被 spring管理, 无法延迟初始化Bean




