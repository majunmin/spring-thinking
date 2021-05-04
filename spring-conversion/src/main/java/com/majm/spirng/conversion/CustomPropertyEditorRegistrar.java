package com.majm.spirng.conversion;

import com.majm.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 13:47
 * @since
 */
@Component // 将其声明为 spring bean
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1. 通用类型转换
        // 2. Java Bean属性类型转换
        registry.registerCustomEditor(User.class, "properties", new StringToPropertiesPropertyEditor());

    }


}
