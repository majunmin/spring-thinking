package com.majm.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-02 15:01
 * @since
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        // StringList --> ArrayList -> AbstractList  --> List  -->Collection
        resolvableType.getSuperType();
        resolvableType.getSuperType().getSuperType();

        System.out.println(resolvableType.asCollection().resolve()); // RawType
        System.out.println(resolvableType.asCollection().resolveGeneric(0)); // 获取泛型参数类型
    }
}
