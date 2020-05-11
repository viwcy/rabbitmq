package com.fuqiang.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.fuqiang.rabbitmq.mapper", "com.fuqiang.basecommons.mapper"})
@ComponentScan(basePackages = {"com.fuqiang.*"})
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
