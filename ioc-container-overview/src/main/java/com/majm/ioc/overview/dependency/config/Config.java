package com.majm.ioc.overview.dependency.config;

import com.majm.domain.SuperUser;
import com.majm.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-11 08:07
 * @since
 */
@ComponentScan
public class Config {

    @Bean
    public User user(){
        User user = new User();
        user.setName("majm");
        user.setAge(18);
        return user;
    }

    @Bean
    public SuperUser superUser() {
        SuperUser user = new SuperUser();
        user.setName("majm");
        user.setAge(18);
        user.setAddress("bj");
        return user;
    }
}
