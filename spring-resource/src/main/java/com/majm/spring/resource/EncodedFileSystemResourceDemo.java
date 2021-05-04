package com.majm.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link org.springframework.core.io.FileSystemResource} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-28 22:25
 * @since
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {

        String currentjavaFilePath = System.getProperty("user.dir") + "/spring-resource/src/main/java/com/majm/spring/resource/EncodedFileSystemResourceDemo.java";
        File file = new File(currentjavaFilePath);
        FileSystemResource systemResource = new FileSystemResource(file);

        EncodedResource encodedResource = new EncodedResource(systemResource, "UTF-8");
        // 字符输入流
        Reader reader = encodedResource.getReader();
        System.out.println(IOUtils.toString(reader));
    }
}
