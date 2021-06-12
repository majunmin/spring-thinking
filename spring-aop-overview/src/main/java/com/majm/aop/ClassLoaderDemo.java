package com.majm.aop;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-06-10 23:40
 * @since
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        while(true){
            ClassLoader parent = classLoader.getParent();
            if (parent == null){
                break;
            }
            System.out.println(parent);
            classLoader = parent;
        }
    }
}
