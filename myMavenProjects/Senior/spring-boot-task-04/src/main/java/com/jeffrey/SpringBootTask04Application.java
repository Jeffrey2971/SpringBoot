package com.jeffrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// 开启异步注解功能
@EnableAsync
// 开启基于注解的定时任务
@EnableScheduling
@SpringBootApplication
public class SpringBootTask04Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTask04Application.class, args);
    }

}
