package com.majm.domain.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-19 08:24
 * @since
 */
public class GenericAPIDemo {

    public static void main(String[] args) {
        //原生类型 primitive types
        Class<?> intClass = int.class;

        // 数组类型 array types
        Class<Object[]> objArrClass = Object[].class;

        // 原始类型 rawTypes
        Class<?> stringClass = String.class;

        // 泛型类型参数
        // java.util.AbstractList<E>
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        System.out.println(parameterizedType);

        // 泛型类型变量
        // E
        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        Stream.of(typeArguments).map(TypeVariable.class::cast).forEach(System.out::println);


    }
}
