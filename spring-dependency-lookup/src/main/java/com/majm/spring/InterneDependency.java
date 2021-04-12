package com.majm.spring;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;

/**
 * spring 内建依赖 </br>
 * <p>
 * 1. {@link org.springframework.context.support.AbstractApplicationContext}
 * - environment      外部化配置 以及Profiles
 * - systemProperties   Java 系统属性
 * - systemEnvironment   操作系统环境变量
 * - messageSource       国际化文案
 * - lifecycleProcessor    LifecycleBean 处理器
 * - applicationEventMulticaster  Spring 事件广播器
 *
 * <p>
 * 2. 注解驱动 spring上下文,内建可查找依赖
 * - {@link AnnotationConfigUtils#CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME}  {@link ConfigurationClassPostProcessor}   处理spring配置类
 * - {@link AnnotationConfigUtils#AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME}  {@link AutowiredAnnotationBeanPostProcessor}  处理 @Autowired  @Value
 * - {@link AnnotationConfigUtils#COMMON_ANNOTATION_PROCESSOR_BEAN_NAME}  {@link CommonAnnotationBeanPostProcessor}        条件激活, 处理 @PostConstruct...
 * - {@link AnnotationConfigUtils#EVENT_LISTENER_PROCESSOR_BEAN_NAME}  {@link EventListenerMethodProcessor}                处理 @EventListener
 * - {@link AnnotationConfigUtils#EVENT_LISTENER_FACTORY_BEAN_NAME}  {@link DefaultEventListenerFactory}                处理 @EventListener
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-12 23:10
 * @since
 */
public class InterneDependency {
}
