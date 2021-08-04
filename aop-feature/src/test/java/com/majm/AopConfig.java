package com.majm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-08-03 07:59
 * @since
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }
}
