package com.jeffrey.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 *
 */

@EnableRabbit // 开启基于注解的 RabbitMQ
@SpringBootApplication
public class SpringBootAmqp02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmqp02Application.class, args);
    }

}
