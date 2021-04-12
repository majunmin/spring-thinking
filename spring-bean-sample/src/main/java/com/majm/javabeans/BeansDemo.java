package com.majm.javabeans;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * java beans </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-10 18:45
 * @since
 */
public class BeansDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);

            // PropertyDescriptor 允许添加属性编辑器 -PropertyEditor 类型转换
            if ("age".equals(propertyDescriptor.getName())) {
                propertyDescriptor.setPropertyEditorClass(String2IntegerPropertyEditor.class);
            }
        });
    }


    static class String2IntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer age = Integer.valueOf(text);
            // 方便后面 getValue()
            super.setValue(age);
        }
    }
}

