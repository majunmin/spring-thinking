package com.majm.bean.scope;

import com.majm.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.time.Instant;
import java.util.Map;

/**
 * BeanScope </br>
 * <p>
 * 1.
 * {@link ConfigurableBeanFactory#SCOPE_SINGLETON}  无论依赖查找还是依赖注入都是同一个对象
 * {@link ConfigurableBeanFactory#SCOPE_PROTOTYPE}  无论依赖查找还是依赖注入都是新生成对象
 * <p>
 * 2.
 * 如果依赖注入集合类型对象,SingletonBean 和 prototypeBean 均会存在一个
 * <p>
 * 3.
 * spring 容器无法管理 {@link ConfigurableBeanFactory#SCOPE_PROTOTYPE} 类型Bean的生命周期,也没有办法记录实例的存在,
 * 销毁回调方法将不会执行,可以利用 {@link BeanPostProcessor} 进行清扫工作
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 14:43
 * @since
 */
public class BeanScopeDemo {

    @Bean
    public static User singletonUser() {
        User user = new User();
        user.setId(Instant.now().toEpochMilli());
        user.setName("majm");
        return user;
    }

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User protoUser() {
        User user = new User();
        user.setId(Instant.now().toEpochMilli());
        user.setName("majm");
        return user;
    }

    @Autowired
    private User prototypeUser1;

    @Autowired
    private User prototypeUser2;

    @Autowired
    private User prototypeUser3;

    @Autowired
    private Map<String, User> userMap;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.println(beanName + "- 正在初始化回调");
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        scopedByLookUp(applicationContext);
        scopedByInjection(applicationContext);


        applicationContext.close();
    }

    private static void scopedByInjection(AnnotationConfigApplicationContext applicationContext) {

        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println(demo.prototypeUser1);
        System.out.println(demo.prototypeUser2);
        System.out.println(demo.prototypeUser3);

    }

    private static void scopedByLookUp(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            // singleton 对象是共享的
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser" + singletonUser);

            User protoUser = applicationContext.getBean("protoUser", User.class);
            System.out.println("protoUser" + protoUser);
        }

    }
}
