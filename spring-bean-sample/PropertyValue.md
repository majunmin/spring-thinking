# PropertyValue



![](https://gitee.com/niubenwsl/image_repo/raw/master/image/java/20210512083652.png)


## AttributeAccessor

定义了对 属性的增删改查

```java
public interface AttributeAccessor {

    void setAttribute(String name, Object value);

    Object getAttribute(String name);

    Object removeAttribute(String name);

    boolean hasAttribute(String name);

    String[] attributeNames();
}
```

`AttributeAccessorSupport`: AttributeAccessor 的默认实现

## BeanMetadataElement 

```java
public interface BeanMetadataElement {

	@Nullable
	default Object getSource() {
		return null;
	}

}

```
定义 Bean的元数据, `getSource()` 返回的是一个配置源对象,表明 AttributeAccessor 要操纵的对象

## BeanMetadataAttribute

> 对某个元数据的封装,<属性名，属性值> 的封装, 是一个持有属性对和源的对象
> 包含三个属性: 1. 属性名  2 属性值  3 配置源对象(冗余了源对象)

```java
public class BeanMetadataAttribute implements BeanMetadataElement {

	private final String name;

	@Nullable
	private final Object value;

	@Nullable
	private Object source;

}
```


## BeanMetadataAttributeAccessor

> BeanMetadataAttributeAccessor 实现了 BeanMetadataElement and  AttributeAccessor， 持有属性  `BeanMetadataAttribute`
> 对AttributeAccessorSupport进行了扩展：增加了addMetadataAttribute和getMetadataAttribute的方法。

## PropertyValue


> 由于PropertyValue继承自BeanMetadataAttributeAccessor，所以，PropertyValue也持有源数据，
> 所以PropertyValue实际上维护了一个这样的信息：源对象，源对象的某个属性，源对象的这个属性的值，并且可以将这个属性值进行最终的确定。

```java

public class PropertyValue extends BeanMetadataAttributeAccessor implements Serializable {

    // 属性名
	private final String name;

	// 属性值
	@Nullable
	private final Object value;

    //是否是可选的，如果是可选的，则表示Bean的这个属性可以不存在
	private boolean optional = false;

    // 属性值是否已经被转换为最终的值
	private boolean converted = false;

    // 最终的已被转换的属性值
	@Nullable
	private Object convertedValue;

    // 属性值是否需要被转换
	/** Package-visible field that indicates whether conversion is necessary. */
	@Nullable
	volatile Boolean conversionNecessary;

	/** Package-visible field for caching the resolved property path tokens. */
	@Nullable
	transient volatile Object resolvedTokens;

}

```

## PropertyValues

PropertyValue 集合

```java
public interface PropertyValues extends Iterable<PropertyValue> {

	@Override
	default Iterator<PropertyValue> iterator() {
		return Arrays.asList(getPropertyValues()).iterator();
	}

	@Override
	default Spliterator<PropertyValue> spliterator() {
		return Spliterators.spliterator(getPropertyValues(), 0);
	}

	default Stream<PropertyValue> stream() {
		return StreamSupport.stream(spliterator(), false);
	}

	PropertyValue[] getPropertyValues();

	@Nullable
	PropertyValue getPropertyValue(String propertyName);

	//  old 和当前的对比, 返回当前有而 old 没有的元素
	PropertyValues changesSince(PropertyValues old);

	boolean contains(String propertyName);

	boolean isEmpty();

}
```

### MutablePropertyValues

PropertyValues的实现, 内部使用 ArrayList 维护 PropertyValue 集合

1. 属性 

```java

	private final List<PropertyValue> propertyValueList;

    // 已经解析过得 PropertyValue
	@Nullable
	private Set<String> processedProperties;

	// 是与否已经转换过
	private volatile boolean converted;

```

2. addPropertyValues

> 可以将 Map、PropertyValue 属性添加到集合中，MutablePropertyValues 统一封装成 PropertyValue 进行存储(ArrayList<PropertyValue>)
> 

```java
	public MutablePropertyValues addPropertyValues(@Nullable Map<?, ?> other) {
		if (other != null) {
			other.forEach((attrName, attrValue) -> addPropertyValue(
					new PropertyValue(attrName.toString(), attrValue)));
		}
		return this;
	}

    public MutablePropertyValues addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < this.propertyValueList.size(); i++) {
        PropertyValue currentPv = this.propertyValueList.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                pv = mergeIfRequired(pv, currentPv);
                setPropertyValueAt(pv, i);
                return this;
            }
        }
        this.propertyValueList.add(pv);
        return this;
    }
```

**注意如果属性值是 Mergeable 类型会先合并，代码如下:**

```java
	private PropertyValue mergeIfRequired(PropertyValue newPv, PropertyValue currentPv) {
		Object value = newPv.getValue();
		if (value instanceof Mergeable) {
			Mergeable mergeable = (Mergeable) value;
			if (mergeable.isMergeEnabled()) {
				Object merged = mergeable.merge(currentPv.getValue());
				return new PropertyValue(newPv.getName(), merged);
			}
		}
		return newPv;
	}
```

**Mergeable 的子类有 ManagedSet、ManagedList、ManagedMap、ManagedProperties、ManagedArray**
合并也很简单,就是合并两个 列表 

3. changesSince

查找相对 old 发生变化的所有元素(old and new 都包含的属性)

```java
	@Override
	public PropertyValues changesSince(PropertyValues old) {
		MutablePropertyValues changes = new MutablePropertyValues();
		if (old == this) {
			return changes;
		}

		// for each property value in the new set
		for (PropertyValue newPv : this.propertyValueList) {
			// if there wasn't an old one, add it
			PropertyValue pvOld = old.getPropertyValue(newPv.getName());
			if (pvOld == null || !pvOld.equals(newPv)) {
				changes.addPropertyValue(newPv);
			}
		}
		return changes;
	}
```










