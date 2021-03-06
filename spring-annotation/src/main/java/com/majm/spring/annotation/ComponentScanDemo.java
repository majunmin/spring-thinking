package com.majm.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.context.annotation.ComponentScan}  sample </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-04 10:29
 * @since
 */
//@ComponentScan(basePackages = "com.majm.spring.annotation")
@MyComponentScan(scanBasePackages = "com.majm.spring.annotation")
public class ComponentScanDemo {

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
