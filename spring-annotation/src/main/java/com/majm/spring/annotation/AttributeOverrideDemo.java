package com.majm.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 元注解属性覆盖 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-08 22:39
 * @since
 */
@MyComponentScan2(scanBasePackages = "#")
public class AttributeOverrideDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ComponentScanDemo.class);
        applicationContext.refresh();

        // MyComponent1 --> MyComponent  --> Component

        // spring 从 4.0 开始支持  多层次  @Component ""派生
        // Annotation --> AnnotationAttributes
        TestClass testClass = applicationContext.getBean(TestClass.class);
        System.out.println(testClass);


        applicationContext.close();
    }
}
