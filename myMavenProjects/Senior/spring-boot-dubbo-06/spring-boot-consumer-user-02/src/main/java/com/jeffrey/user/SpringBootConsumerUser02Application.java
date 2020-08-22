package com.jeffrey.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消费者
 * 1、引入依赖
 * 2、配置 Dubbo 的注册中心地址
 * 3、引用服务
 */
@SpringBootApplication
public class SpringBootConsumerUser02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerUser02Application.class, args);
    }

}
