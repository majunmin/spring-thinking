package com.majm.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.List;

/**
 * {@link GenericTypeResolver} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-02 12:04
 * @since
 */
public class GenericTypeResolveDemo {

    public static void main(String[] args) throws NoSuchMethodException {

        Method method = GenericTypeResolveDemo.class.getDeclaredMethod("getString");

        Class<?> aClass = GenericTypeResolver.resolveReturnType(method, GenericTypeResolveDemo.class);
        // 常规类型返回
        System.out.println(aClass == String.class);

        // 常规类型不具备泛型参数类型
        System.out.println();

        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, String.class, "getString");
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getList");
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getStringList");

    }

    public static void displayReturnTypeGenericInfo(Class<?> containClass, Class<?> superClass, String methodName, Class<?>... arguments) throws NoSuchMethodException {
        Method method = containClass.getDeclaredMethod(methodName);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containClass);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = [%s] \n", methodName, containClass, returnType);

        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, superClass);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = [%s] \n", methodName, superClass, returnTypeArgument);
    }

    public static String getString() {
        return "";
    }

    public static <E> List<E> getList() {
        return null;
    }

    public static StringList getStringList() { // 泛型参数具体化(字节码中有体现)
        return null;
    }
}
