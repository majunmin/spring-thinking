# PropertySourceResolver

> spring 框架 将属性源抽象成了 类 PropertySource, 又将多个属性源组合抽象为接口 `PropertySources`,
> 对 某个`PropertySource`中的属性的解析工作抽象成了 接口 `PropertyResolver`(委托),
> 类PropertySourcesPropertyResolver则是Spring用于解析一个PropertySources对象中属性的工具类.
>
> Spring应用 `Environment`对象中对其 `PropertySources`对象的属性解析,就是通过这样一个对象(代理)


![](https://gitee.com/niubenwsl/image_repo/raw/master/image/java/20210511233452.png)

```java
/**
 * 一个PropertyResolver实现类，用于解析一个PropertySources对象中的属性源集合中的属性。
 *
 *
 */
public class PropertySourcesPropertyResolver extends AbstractPropertyResolver {

    //  PropertySources形式存在的属性源集合, 该工具的工作对象
    @Nullable
    private final PropertySources propertySources;


    /**
     * Create a new resolver against the given property sources.
     * @param propertySources the set of {@link PropertySource} objects to use
     */
    public PropertySourcesPropertyResolver(@Nullable PropertySources propertySources) {
        this.propertySources = propertySources;
    }


    // 查看是否包含某个指定名称的属性,判断方法:
    // 1. 底层任何一个属性源包含该属性的话就认为是包含
    // 2. 否则认为是不包含
    @Override
    public boolean containsProperty(String key) {
        if (this.propertySources != null) {
            for (PropertySource<?> propertySource : this.propertySources) {
                if (propertySource.containsProperty(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 获取某个指定名称的属性的值，如果该属性不被包含的话，返回null
    // 不管该属性的值是什么类型，将它转换成字符串类型
    @Override
    @Nullable
    public String getProperty(String key) {
        return getProperty(key, String.class, true);
    }

    // 获取某个指定名称的属性的值，并将其转换成指定的类型，如果该属性不被包含的话，返回null
    @Override
    @Nullable
    public <T> T getProperty(String key, Class<T> targetValueType) {
        return getProperty(key, targetValueType, true);
    }

    // 获取某个指定名称的属性的值，如果该属性不被包含的话，返回null
    // 不管该属性的值是什么类型，将它转换成字符串类型
    @Override
    @Nullable
    protected String getPropertyAsRawString(String key) {
        return getProperty(key, String.class, false);
    }

    // 获取某个指定名称的属性的值，并将其转换成指定的类型，如果该属性不被包含的话，返回null
    // resolveNestedPlaceholders : 是否要解析属性值中包含的占位符
    @Nullable
    protected <T> T getProperty(String key, Class<T> targetValueType, boolean resolveNestedPlaceholders) {
        if (this.propertySources != null) {
            // 遍历每个属性源,如果发现目标属性被某个属性源包含,则获取它的值并按要求做相应的处理然后返回处理
            // 后的值从这里使用for循环的方式来看，可以将属性源看作是一个List,(FIFO)
            // 索引较小的属性源先被访问 :-> 索引较小的属性源具有较高优先级
            for (PropertySource<?> propertySource : this.propertySources) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Searching for key '" + key + "' in PropertySource '" +
                            propertySource.getName() + "'");
                }
                Object value = propertySource.getProperty(key);
                if (value != null) {
                    if (resolveNestedPlaceholders && value instanceof String) {
                        // 解析值中的占位符
                        value = resolveNestedPlaceholders((String) value);
                    }
                    logKeyFound(key, propertySource, value);
                    // 根据要求做相应的类型转换然后返回转换后的值
                    return convertValueIfNecessary(value, targetValueType);
                }
            }
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Could not find key '" + key + "' in any property source");
        }
        return null;
    }

    /**
     * Log the given key as found in the given {@link PropertySource}, resulting in
     * the given value.
     * <p>The default implementation writes a debug log message with key and source.
     * As of 4.3.3, this does not log the value anymore in order to avoid accidental
     * logging of sensitive settings. Subclasses may override this method to change
     * the log level and/or log message, including the property's value if desired.
     * @param key the key found
     * @param propertySource the {@code PropertySource} that the key has been found in
     * @param value the corresponding value
     * @since 4.3.1
     */
    protected void logKeyFound(String key, PropertySource<?> propertySource, Object value) {
        if (logger.isDebugEnabled()) {
            logger.debug("Found key '" + key + "' in PropertySource '" + propertySource.getName() +
                    "' with value of type " + value.getClass().getSimpleName());
        }
    }

}
```

