# Spring 类型转换

使用场景  |基于javabeans 接口的类型转换 | Spring3.0 的通用类型转换
| ---- |  ---- | ---- |
|1. 数据绑定        | Yes  | Yes |
|2. BeanWrapper    | Yes  | Yes |
|3. Bean属性类型转换 | Yes  | Yes |
|4. 外部化属性类型转换 | No  | Yes |

## spring 内建 PropertyEditor

- String -> Byte[]    `org.springframework.beans.propertyeditors.ByteArrayPropertyEditor`
- String -> Char
- String -> Char[]
- String -> CharSet
- String -> Class
- String -> Currency

## Spring3 通用类型转换 ConversionService

- org.springframework.core.convert.converter.Converter
- org.springframework.core.convert.converter.ConditionalConverter
- org.springframework.core.convert.ConversionService

## Spring 内建 类型转换器

内建扩展

1. 时间日期相关 org.springframework.core.datetime
2. java8时间日期 org.springframework.core.datetime.standard
3. 通用实现 org.springframework.core.converter.support

## Converter 接口局限性

1. 缺少 sourceType targetType 的前置判断
    * 使用 org.springframework.core.convert.converter.ConditionalConverter
2. 仅能转换单一的 sourceType to targetType
    * 使用 org.springframework.core.convert.converter.GenericConverter

```java
// 类型转换服务
public interface ConversionService {

    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType);

    @Nullable
    <T> T convert(@Nullable Object source, Class<T> targetType);

    @Nullable
    Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType);

}


```

## 三种类型的转换器的添加和扣除

```java
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    <S, T> void addConverter(Class<S> sourceType, Class<T> targetType, Converter<? super S, ? extends T> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> factory);

    void removeConvertible(Class<?> sourceType, Class<?> targetType);

}
```

## DefaultConversionService

ConversionService 的一个默认实现 `DefaultConversionService`, 这个类在初始化时会添加spring默认的转换器, 大部分时候使用这个实现就可以完成所需要的功能

```java
public class DefaultConversionService extends GenericConversionService {

    @Nullable
    private static volatile DefaultConversionService sharedInstance;

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    // 返回默认的 ConversionService (单例模式)
    // 双重检查(添加局部变量的 意思是 提升性能)
    public static ConversionService getSharedInstance() {
        DefaultConversionService cs = sharedInstance;
        if (cs == null) {
            synchronized (DefaultConversionService.class) {
                cs = sharedInstance;
                if (cs == null) {
                    cs = new DefaultConversionService();
                    sharedInstance = cs;
                }
            }
        }
        return cs;
    }

    // 添加 默认类型转换器
    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 1. 添加基础的 标准转换器
        addScalarConverters(converterRegistry);
        // 2. 添加 集合类型转换器
        addCollectionConverters(converterRegistry);

        converterRegistry.addConverter(new ByteBufferConverter((ConversionService) converterRegistry));
        converterRegistry.addConverter(new StringToTimeZoneConverter());
        converterRegistry.addConverter(new ZoneIdToTimeZoneConverter());
        converterRegistry.addConverter(new ZonedDateTimeToCalendarConverter());

        converterRegistry.addConverter(new ObjectToObjectConverter());
        converterRegistry.addConverter(new IdToEntityConverter((ConversionService) converterRegistry));
        converterRegistry.addConverter(new FallbackObjectToStringConverter());
        converterRegistry.addConverter(new ObjectToOptionalConverter((ConversionService) converterRegistry));
    }

}
```


## GenericConversionService

GenericConversionService 实现了  ConversionService 和 ConverterRegistry 接口
DefaultConversionService 就是基于 GenericConversionService 的扩展
```java

```












