package com.majm.firstspringbootapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@RestController
@SpringBootApplication
public class FirstSpringBootApplication {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/index")
    public String index() {
        return "hello world :" + port;
    }


    @Bean
    @ConditionalOnClass(value = {String.class, Integer.class})
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/helloWorld"), serverRequest ->
                ok().body(Mono.just("hello World"), new ParameterizedTypeReference<Mono<? super String>>() {})
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringBootApplication.class, args);
    }

}
