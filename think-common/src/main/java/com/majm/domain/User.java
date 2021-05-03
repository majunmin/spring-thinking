package com.majm.domain;


import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-10 23:22
 * @since
 */
public class User implements BeanNameAware {

    private Long id;

    private String name;

    private Integer age;

    private City city;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private Properties properties;

    /**
     * 当前beanName
     */
    private transient String beanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println(this.beanName + ": 用户信息初始化回调... ");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(this.beanName + ": 用户信息销毁回调...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
