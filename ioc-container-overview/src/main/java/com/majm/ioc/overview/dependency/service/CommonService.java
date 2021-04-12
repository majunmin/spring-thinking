package com.majm.ioc.overview.dependency.service;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 08:46
 * @since
 */
@Service
public class CommonService {

    @Autowired
    private User user;

    @Autowired
    private SuperUser superUser;

    @Autowired
    private ObjectFactory<User> userObjectFactory;

    @Autowired
    private ObjectFactory<ApplicationContext> apFactory;

    @Autowired
    private List<User> userList;

    @Autowired
    private BeanFactory beanFactory;  // 内建对象 非Bean,

    @Autowired
    private ObjectFactory<BeanFactory> objectFactory;

    public CommonService() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SuperUser getSuperUser() {
        return superUser;
    }

    public void setSuperUser(SuperUser superUser) {
        this.superUser = superUser;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<BeanFactory> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<BeanFactory> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public ObjectFactory<ApplicationContext> getApFactory() {
        return apFactory;
    }

    public void setApFactory(ObjectFactory<ApplicationContext> apFactory) {
        this.apFactory = apFactory;
    }
}
