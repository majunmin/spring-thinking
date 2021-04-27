package com.majm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * 持有User的 Holder  </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-15 09:14
 * @since
 */
public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware {

    private User user;
    private Integer number;
    private String description;

    private ClassLoader classLoader;
    private String beanName;
    private BeanFactory beanFactory;
    private Environment environment;


    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("execuete setBeanClassLoader");
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("execuete setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("execuete setBeanName");
        this.beanName = beanName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("execuete setEnvironment");
        this.environment = environment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", classLoader=" + classLoader +
                ", beanName='" + beanName + '\'' +
                ", beanFactory=" + beanFactory +
                ", environment=" + environment +
                '}';
    }
}
