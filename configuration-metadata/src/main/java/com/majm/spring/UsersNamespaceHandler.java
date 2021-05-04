package com.majm.spring;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * "user.xsd" {@link NamespaceHandler} 实现 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-25 23:47
 * @since
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {


    @Override
    public void init() {
        // 注册 user 元素 对应的 BeanDefinitionParser
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
