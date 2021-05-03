# Spring 国际化支持

MessageResource 的两个实现

* MessageSource
    - org.springframework.context.support.ReloadableResourceBundleMessageSource
    - org.springframework.context.support.ResourceBundleMessageSource

## 层次性接口

> ClassLoader 双亲委派模型

`org.springframework.beans.factory.HierarchicalBeanFactory`
`org.springframework.context.ApplicationContext`
`org.springframework.beans.factory.config.BeanDefinition`
`org.springframework.context.HierarchicalMessageSource`

## java标准国际化接口 ResourceBundle

核心接口

- 抽象实现: `java.util.ResourceBundle`
- Properties 资源实现: `java.util.PropertyResourceBundle`
- 例举实现 : `java.util.ListResourceBundle`

核心特性

- key-value 设计
- 层次性
- 缓存设计
- 字符编码控制 `java.util.ResourceBundle.Control`
- Control SPI 扩展 `java.util.spi.ResourceBundleControlProvider` (@since1.8)

## java 文本格式化

- 核心接口
    * java.text.MessageFormat

- 基本用法
    * 设置消息格式模式 new MessageForamt()
    * format(new Object[]{})

- 消息格式模式
    * 格式元素
    * FormatType 消息格式类型, 可选项, number, data, time, choice
    * FormatStyle 消息格式风格 可选项, long, short, full, integer, currency

## MessageSource 开箱即用的实现

- ResourceBundle + MessageFormat 组合实现 MessageSource
    * `org.springframework.context.support.ResourceBundleMessageSource`
- Properties + MessageFormat 组合 实现 MessageSource
    * `org.springframework.context.support.ReloadableResourceBundleMessageSource`

## MessageSource spring内建依赖

> 对后序分析 SpringBoot有帮助

- MessageSource 内建bean 可能来源
    * 预注册的 Bean, name = `AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME`, type = MessageSource 的bean
    * 默认内建实现 DelegatingMessageSource
        * 层次性查找 MessageSource

`MessageSource` 在 `ApplicationContext` 的关系是一种代理模式或者是 装饰器模式，
`ApplicationContext` 实现了  `MessageSource` 接口, ApplicationContext 包含一个 成员变量 messageSource,
`ApplicationContext#getMessage()` 的动作全部委派给了 内建的 MessageSource

MessageSource 的初始化(在 AbstractApplicationContext 中进行)

```java

public void refresh()throws BeansException,IllegalStateException{
        // Initialize message source for this context.
        // 初始化 MessageSource
        initMessageSource();
}

protected void initMessageSource() {
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    if (beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME)) {
        this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
        // Make MessageSource aware of parent MessageSource.
        if (this.parent != null && this.messageSource instanceof HierarchicalMessageSource) {
            HierarchicalMessageSource hms = (HierarchicalMessageSource) this.messageSource;
            if (hms.getParentMessageSource() == null) {
                // Only set parent context as parent MessageSource if no parent MessageSource
                // registered already.
                hms.setParentMessageSource(getInternalParentMessageSource());
            }
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Using MessageSource [" + this.messageSource + "]");
        }
    }
    else {
        // Use empty MessageSource to be able to accept getMessage calls.
        DelegatingMessageSource dms = new DelegatingMessageSource();
        dms.setParentMessageSource(getInternalParentMessageSource());
        this.messageSource = dms;
        beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
        if (logger.isTraceEnabled()) {
            logger.trace("No '" + MESSAGE_SOURCE_BEAN_NAME + "' bean, using [" + this.messageSource + "]");
        }
    }
}

```



## SpringBoot 为什么会创建一个 MessageSource Bean

- AbstractApplicationContext 的实现决定了 MessageSource 的实现
- SpringBoot 通过外部化配置简化 MessageSourceBean 构建(MessageSourceAutoConfiguration)
- SpringBoot 基于 BeanValidation 校验非常普遍(MessageSource 可以去桥接校验的一些逻辑,提供一些相应的文案)



--------------
ServiceLoader#load()  vs ServiceLoader#loadInstalled()
[为什么我的ResourceBundleControlProvider没有加载？](https://www.thinbug.com/q/24195777)









