package com.majm.spirng.conversion;

import java.beans.PropertyEditor;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-01 13:36
 * @since
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        String text = "name=马俊民";

        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());
    }
}
