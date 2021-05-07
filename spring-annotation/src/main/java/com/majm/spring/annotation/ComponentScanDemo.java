package com.majm.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link org.springframework.context.annotation.ComponentScan}  sample </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-04 10:29
 * @since
 */
@ComponentScan(basePackages = "com.majm.spring.annotation")
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ComponentScanDemo.class);
        applicationContext.refresh();

        // MyComponent1 --> MyComponent  --> Component
        TestClass testClass = applicationContext.getBean(TestClass.class);
        System.out.println(testClass);


        applicationContext.close();
    }
}
