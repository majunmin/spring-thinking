package com.majm.spring.generic;

import javax.lang.model.type.PrimitiveType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java 泛型 API示例 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 13:32
 * @since
 */
public class GenericAPIDemo {

    public static void main(String[] args) {

        // 原生类型  primitive Type
        Class aClass = int.class;

        // 数组类型  arrayType []int []String
        Class<Object[]> arrClass = Object[].class;

        // 原始类型 rawType java.lang.String.class
        Class<String> stringClass = String.class;

        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

//        AbstractList<E>
        System.out.println(parameterizedType.toString());

        // E
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        Arrays.stream(typeArguments).map(TypeVariable.class::cast).forEach(System.out::println);


    }
}
