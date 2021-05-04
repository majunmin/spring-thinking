package com.majm.spring;

import com.majm.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * user 元素的 {@link BeanDefinitionParser} 实现 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-25 23:52
 * @since
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setAttributeValue("id", element, builder);
        setAttributeValue("name", element, builder);
        setAttributeValue("city", element, builder);
    }

    private void setAttributeValue(String attrName, Element element, BeanDefinitionBuilder builder) {
        String attrVal = element.getAttribute(attrName);
        if (StringUtils.hasText(attrVal)) {
            builder.addPropertyValue(attrName, attrVal);
        }
    }
}
