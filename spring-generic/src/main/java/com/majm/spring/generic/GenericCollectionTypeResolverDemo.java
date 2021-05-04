package com.majm.spring.generic;

import java.util.List;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 22:53
 * @since
 */
public class GenericCollectionTypeResolverDemo {

    private static StringList stringList;
    private static List<String> strings;

    public static void main(String[] args) {

        // StringList extends ArrayList<String> 具体化
        // getCollectionType 返回具体化泛型参数类型集合的成员类型 = String
    }
}
