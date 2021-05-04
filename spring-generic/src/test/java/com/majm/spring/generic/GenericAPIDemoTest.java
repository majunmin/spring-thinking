package com.majm.spring.generic;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;


public class GenericAPIDemoTest {

    private Service<Double, Float> service;
    private List<List<String>> list;
    private Map<String, Map<String, Integer>> map;
    private List<String>[] array;


    @Test
    public void fotClassTest() {
        ResolvableType resolvableType = ResolvableType.forClass(ServiceImpl.class);
        Assert.assertEquals(ServiceImpl.class, resolvableType.getType());
        Assert.assertEquals(ServiceImpl.class, resolvableType.resolve());
    }

    @Test
    public void testField() {
        Field field = ReflectionUtils.findField(GenericAPIDemoTest.class, "service");
        ResolvableType resolvableType = ResolvableType.forField(field);

        // ResolvableType#getType() 保存原始Type类型
        Assert.assertEquals(field.getGenericType(), resolvableType.getType());
        // ResolvableType#rresolvable()  类型保存的是   Service.class
        Assert.assertEquals(((ParameterizedType) field.getGenericType()).getRawType(), resolvableType.resolve());

        Class<?> clazz = resolvableType.getGeneric(0).resolve();
        Assert.assertEquals(Double.class, clazz);

        resolvableType = ResolvableType.forField(ReflectionUtils.findField(GenericAPIDemoTest.class, "list"));

        // 下面两种获取泛型的方式等价
        clazz = resolvableType.getGeneric(0).getGeneric(0).resolve();
        Assert.assertEquals(String.class, clazz);
        clazz = resolvableType.getGeneric(0, 0).resolve();
        Assert.assertEquals(String.class, clazz);

        // 3. Map<String, Map<String, Integer>> map
        resolvableType = ResolvableType.forField(
                ReflectionUtils.findField(GenericAPIDemoTest.class, "map"));
        clazz = resolvableType.getGeneric(1).getGeneric(1).resolve();
        Assert.assertEquals(Integer.class, clazz);


        resolvableType = ResolvableType.forField(ReflectionUtils.findField(GenericAPIDemoTest.class, "array"));
        Assert.assertTrue(resolvableType.isArray());
        Assert.assertEquals(List.class, resolvableType.getComponentType().resolve());
        Assert.assertEquals(String.class, resolvableType.getComponentType().getGeneric(0).resolve());
    }

    @Test
    public void forMethodTest() {
        // 方法返回值类型
        ResolvableType returnType =
                ResolvableType.forMethodReturnType(ReflectionUtils.findMethod(ServiceImpl.class, "method"));
        Assert.assertEquals(Double.class, returnType.getGeneric(1, 0).resolve());

        // public ServiceImpl(List<List<String>> list, Map<Double, Map<Float, Integer>> map) {}
        ResolvableType parameterType =
                ResolvableType.forConstructorParameter(ClassUtils.getConstructorIfAvailable(ServiceImpl.class, List.class, Map.class), 0);
        Assert.assertEquals(String.class, parameterType.getGeneric(0, 0).resolve());

        parameterType =
                ResolvableType.forConstructorParameter(ClassUtils.getConstructorIfAvailable(ServiceImpl.class, List.class, Map.class), 1);
        Assert.assertEquals(Double.class, parameterType.getGeneric(0).resolve());
        Assert.assertEquals(Float.class, parameterType.getGeneric(1, 0).resolve());
        Assert.assertEquals(Integer.class, parameterType.getGeneric(1, 1).resolve());
    }

}













