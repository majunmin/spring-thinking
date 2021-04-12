package com.majm.ioc.overview.dependency.injection;

import com.majm.ioc.overview.dependency.config.Config;
import com.majm.ioc.overview.dependency.service.CommonService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 08:12
 * @since
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config.class);
        applicationContext.register(DependencyInjectionDemo.class);
        applicationContext.refresh();

        CommonService commonService = applicationContext.getBean(CommonService.class);

        // 依赖注入
        System.out.println(commonService.getBeanFactory());

        // 依赖查找 `No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available`
        // System.out.println(applicationContext.getBean(BeanFactory.class));


        System.out.println(applicationContext == commonService.getBeanFactory());//false
        System.out.println(applicationContext == commonService.getBeanFactory());//false
        System.out.println(applicationContext == commonService.getObjectFactory().getObject());//false
        System.out.println(applicationContext == commonService.getApFactory().getObject());  // true
    }

    @Bean
    public CommonService commonService() {
        return new CommonService();
    }
}
