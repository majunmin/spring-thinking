# Resource




## java 资源加载




`java.protocol.handler.pkgs`



## Spring资源接口

| 类型 | 接口 |
| ---- | ---- |
|输入流 |     org.springframework.core.io.InputStreamSource  |
|只读资源 |   org.springframework.core.io.Resource   |
|可写资源 | org.springframework.core.io.WritableResource  |
|编码资源  |  org.springframework.core.io.support.EncodedResource  |
|上下文资源 | org.springframework.core.io.ContextResource |

InputStreamSource:
只有一个方法 `InputStreamSource#getInputStream()`, 该接口允许获取资源的输入流

Resource 只读接口,继承了 InputStreamSource, 对资源仅提供读取接口 

```java
public interface Resource extends InputStreamSource {

	boolean exists();

	default boolean isReadable() { return exists();}
	
    default boolean isOpen() { return false;}

	default boolean isFile() { return false; }

	URL getURL() throws IOException;

	URI getURI() throws IOException;

	File getFile() throws IOException;

	default ReadableByteChannel readableChannel() throws IOException { return Channels.newChannel(getInputStream()); }

	long contentLength() throws IOException;

	long lastModified() throws IOException;

	Resource createRelative(String relativePath) throws IOException;
	
	@Nullable
	String getFilename();
	
	String getDescription();

}
```

WritableResource  可写资源
`WritableResource#isWritable()`判断资源是否可写, `WritableResource#getoutputStream()` 获取输出流

EncodedResource 
针对资源进行编码的资源

ContextResource
一般是给 Servlet 引擎使用






## Spring 内建 Resource 实现

org.springframework.beans.factory.support.BeanDefinitionResource
org.springframework.core.io.ByteArrayResource
org.springframework.core.io.ClassPathResource
org.springframework.core.io.UrlResource
org.springframework.core.io.FileSystemResource













