package com.majm.spirng.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 13:34
 * @since
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    // 1. 实现 setAsText()
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 2. 将 string 转换为  Properties
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
           throw new IllegalArgumentException();
        }

        //  3. 临时存储  properties 对象
        setValue(properties);

        // next  获取临时对象 Properties

    }
}
