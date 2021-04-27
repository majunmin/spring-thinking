package com.majm.spring.annotation.server;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * ServerImportSelector </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-27 23:06
 * @since
 */
public class ServerImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 读取 EnableServer 中的所有属性方法,本例中仅有 "type()"
        // 其中 key: 属性方法名 value: 属性方法返回值
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getCanonicalName());
        Server.Type serveType = ((Server.Type) annotationAttributes.get("type"));
        //导入类的名称数组
        String[] importClassNames = new String[0];
        switch (serveType) {
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getCanonicalName()};
                break;
            case FTP:
                importClassNames = new String[]{FTPServer.class.getCanonicalName()};
                break;
            default:
        }
        return importClassNames;
    }
}
