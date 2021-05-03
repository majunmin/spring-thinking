# Spring 事件处理

## Spring 标准事件

- Java标准事件 java.util.EventObject
- Spring应用上下文 ApplicationEvent 扩展 ApplicationContextEvent
    * spring应用上下文作为事件源
    * 实现
        - org.springframework.context.event.ContextClosedEvent
        - org.springframework.context.event.ContextRefreshedEvent
        - org.springframework.context.event.ContextStartedEvent
        - org.springframework.context.event.ContextStoppedEvent

## Spring 事件监听器

1. java标准事件监听器 - java.util.EventListener 扩展
    - 扩展接口 `org.springframework.context.ApplicationListener`
    - 涉及特点 单一类型事件处理
    - 处理方法  `onApplicationEvent(E event)`
    - 事件类型  `org.springframework.context.ApplicationEvent`

## EventListener 注解实现

`org.springframework.context.event.EventListener`

- 支持多 ApplicationEvent 类型, 无需接口约束
- 支持异步执行
- 支持泛型类型事件
- 支持顺序控制(@Order)

## Spring 事件发布器

- ApplicationEventPublisher(依赖注入)
- ApplicationEventMulticaster(依赖注入 依赖查找)

## Spring层次性上下文 事件传播

当spring应用出现多层次应用上下文场景时, 如 Spring WebMvc, SpringBoot, SpringCloud, 由子ApplicationContext 发起的spring事件可能会传递到其Parent
ApplicationContext(直到 Root)

> 如何避免:
> 定位spring事件源(ApplicaitonContext)进行过滤处理
>
>

## Spring 内建事件

- ContextRefreshedEvent
- ContextStartedEvent
- ContextStoppedEvent
- ContextClosedEvent

## ApplicationEventMulticaster

```java
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void addApplicationListenerBean(String listenerBeanName);

    void removeApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListenerBean(String listenerBeanName);

    void removeApplicationListeners(Predicate<ApplicationListener<?>> predicate);

    void removeApplicationListenerBeans(Predicate<String> predicate);

    void removeAllListeners();

    void multicastEvent(ApplicationEvent event);

    void multicastEvent(ApplicationEvent event, @Nullable ResolvableType eventType);

}
```

ApplicationEventMulticaster 的 注入地方

- `AbstractApplicationContext#refresh()`
- `org.springframework.context.support.AbstractApplicationContext#initApplicationEventMulticaster`

```java
    //  AbstractApplicationContext.applicationEventMulticaster  初始化后就不会为空
protected void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory=getBeanFactory();
        if(beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)){
        this.applicationEventMulticaster=
        beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,ApplicationEventMulticaster.class);
        if(logger.isTraceEnabled()){
        logger.trace("Using ApplicationEventMulticaster ["+this.applicationEventMulticaster+"]");
        }
        }
        else{
        this.applicationEventMulticaster=new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,this.applicationEventMulticaster);
        if(logger.isTraceEnabled()){
        logger.trace("No '"+APPLICATION_EVENT_MULTICASTER_BEAN_NAME+"' bean, using "+
        "["+this.applicationEventMulticaster.getClass().getSimpleName()+"]");
        }
        }
        }
```

## ApplicationEventPublisher 底层实现

- `org.springframework.context.event.ApplicationEventMulticaster`
- `org.springframework.context.event.AbstractApplicationEventMulticaster`
- `org.springframework.context.event.SimpleApplicationEventMulticaster`

`ApplicationEventPublisher.publishEvent(ApplicationEvent) `的底层实现是通过委托  `ApplicationEventMulticaster` 来实现的 通过
AbstractApplicationContext 进行桥接

```java
    protected void publishEvent(Object event,@Nullable ResolvableType eventType){
        Assert.notNull(event,"Event must not be null");

        // Decorate event as an ApplicationEvent if necessary
        ApplicationEvent applicationEvent;
        if(event instanceof ApplicationEvent){
        applicationEvent=(ApplicationEvent)event;
        }
        else{
        applicationEvent=new PayloadApplicationEvent<>(this,event);
        if(eventType==null){
        eventType=((PayloadApplicationEvent<?>)applicationEvent).getResolvableType();
        }
        }

        // Multicast right now if possible - or lazily once the multicaster is initialized
        if(this.earlyApplicationEvents!=null){
        this.earlyApplicationEvents.add(applicationEvent);
        }
        else{
        getApplicationEventMulticaster().multicastEvent(applicationEvent,eventType);
        }

        // Publish event via parent context as well ...
        if(this.parent!=null){
        if(this.parent instanceof AbstractApplicationContext){
        ((AbstractApplicationContext)this.parent).publishEvent(event,eventType);
        }
        else{
        this.parent.publishEvent(event);
        }
        }
        }
```

### earlyApplicationEvents 作用？

在 AbstractApplicationContext#initApplicationEventMulticaster() 调用之前, BeanPostProcessor BeanFactoryPostProcessor
被调用时添加的事件不能被发布, 所以先暂存在 `earlyApplicationEvents`，之后在 `AbstractApplicationContext.registerListeners()`方法中进行早期事件回放

## 异步事件处理

`@EnableAsync` + `@Async`
`@EnableAsync`: 开启 spring 异步功能,一般在 方法上加上 @Async, 就可以让该方法变为 异步执行方法

异步处理事件的线程池 不宜过多, 一个就好了， 多了的话可能会造成线程资源浪费

```java

@Import(AsyncConfigurationSelector.class)
public @interface EnableAsync {...
}


public class AsyncConfigurationSelector extends AdviceModeImportSelector<EnableAsync> {

    private static final String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME =
            "org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";

    @Override
    @Nullable
    public String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
                return new String[]{ProxyAsyncConfiguration.class.getName()};
            case ASPECTJ:
                return new String[]{ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME};
            default:
                return null;
        }
    }

}

```

## SpringBoot | SpringCloud 中的事件

| SpringBot 事件 | 发生时机 |
| ---- | ---- |
| ApplicationStartingEvent | springBoot应用已启动时 |
| ApplicationStartedEvent | springBoot应用已启动时 |
| ApplicationEnvironmentPreparedEvent | springBoot Environment 实例已准备时 | 
| ApplicationPreparedEvent | springBoot 预备时 |
| ApplicationReadyEvent | springBoot 应用完全可用时| 
| ApplicationFailedEvent | springBoot应用启动失败时| 

| SpringCloud 事件 | 发生时机 |
| ---- | ---- |
| EnvironmentChangeEvent | 当 environment 实例属性发生变化时 |
| HeartBeatEvent | 当Discovery Client 客户端发送心跳时 |
| InstancePreRegisteredEvent | 服务实例注册前 | 
| InstanceRegisteredEvent | 当服务实例注册后 |
| RefreshEvent | 当 RefreshEndpoint 被调用后| 
| RefreshScopeRefreshedEvent | 当 refreshScope Bean 刷新后| 

------------------------
[Spring @EnableAsync 注解原理](https://plentymore.github.io/2018/12/29/Spring-EnableAsync-%E6%B3%A8%E8%A7%A3%E5%8E%9F%E7%90%86/)