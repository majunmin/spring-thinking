package com.majm.spring.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Java 泛型示例  运行时擦除 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 13:48
 * @since
 */
public class GenericDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");

        Collection c = list;
        c.add(1);

        c.forEach(System.out::println);
    }
}
