package com.majm.dependency.injection;

import com.majm.dependency.injection.annotation.InjectedUser;
import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 依赖处理过程 </br>
 * <p>
 * 1. 入口: {@link  DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String)}
 * 2. {@link  DependencyDescriptor}
 * 3. {@link org.springframework.beans.factory.support.AutowireCandidateResolver} 自动绑定候选对象处理器
 * <p>
 * - 元信息解析
 * - 依赖查找
 * - 依赖注入 (字段方法)
 * <p>
 * <p>
 * DefaultListableBeanFactory#preInstantiateSingletons()
 * AbstractBeanFactory#getBean(String)
 * AbstractBeanFactory#doGetBean
 * DefaultSingletonBeanRegistry#getSingleton(String, ObjectFactory<?>)
 * AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])
 * AbstractAutowireCapableBeanFactory#doCreateBean()
 * AbstractAutowireCapableBeanFactory#applyMergedBeanDefinitionPostProcessors
 * `MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition()`  -> 元信息查找
 * AbstractAutowireCapableBeanFactory#populateBean()
 * `AutowiredAnnotationBeanPostProcessor#postProcessProperties()`  -> 依赖注入
 * InjectionMetadata#inject
 * InjectionMetadata.InjectedElement#inject
 * AutowireCapableBeanFactory#resolveDependency(DependencyDescriptor, String, Set<String>, TypeConverter)
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-15 23:01
 * @since
 */
@Configuration
public class AnnotationDependencyResolvableDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    @Inject
    private User injectUser;

    @InjectedUser
    private User myAutowiredUser;

    @Autowired
    private User user; // 必须(required = true) + 实时注入(eager = true) + 通过类型查找(User.class) + 字段名称(user)

    // 集合类型 依赖注入
    @Autowired
    private Map<String, User> users;


    @Bean
    @Primary
    public User user() {
        return new User();
    }

    @Bean
    public SuperUser superUser() {
        SuperUser superUser = new SuperUser();
        superUser.setAddress("hangzhou");
        return superUser;
    }


    /**
     * 如果 @Bean 标注的是 静态方法
     * 那么这个 Bean初始化的时机 不随 包含它的实例初始化而初始化
     * static 标注的方法的bean 会提前触发初始化
     *
     * @return
     */
//    @Bean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 替换原有注解, 使用新注解 @InjectedUser
//        Set<Class<? extends Annotation>> autoiredAnnotationTypes =
//                new LinkedHashSet<>(Arrays.asList(Autowired.class, Value.class, Inject.class, InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autoiredAnnotationTypes);
//        return beanPostProcessor;
//    }

    /**
     * 新老注解同时生效   static
     * 新的  beanName 不能是  AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME
     * because:
     * <p>
     * if (!registry.containsBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)) {
     * RootBeanDefinition def = new RootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class);
     * def.setSource(source);
     * beanDefs.add(registerPostProcessor(registry, def, AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
     * }
     * <p>
     * 如果beanName 存在的话, 就不会进行注册 AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME
     *
     * @return
     */
    @Bean
    public AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyResolvableDemo.class);
        applicationContext.refresh();

        AnnotationDependencyResolvableDemo demo = applicationContext.getBean(AnnotationDependencyResolvableDemo.class);

        System.out.println(demo.user == demo.injectUser);
        System.out.println(demo.user == demo.lazyUser);
        System.out.println(demo.myAutowiredUser);
        applicationContext.close();
    }
}
