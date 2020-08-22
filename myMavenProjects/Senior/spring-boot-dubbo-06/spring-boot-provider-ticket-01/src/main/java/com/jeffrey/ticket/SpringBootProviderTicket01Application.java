package com.jeffrey.ticket;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 将服务提供者注册到注册中心
 *      1、引入 Dubbo 和 zkclient 相关依赖
 *      2、配置 Dubbo 的扫描包和注册中心地址
 *      3、使用 @Service 发布服务
 */

@EnableDubbo
@SpringBootApplication
public class SpringBootProviderTicket01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProviderTicket01Application.class, args);
    }

}
