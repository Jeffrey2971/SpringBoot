package com.jeffrey.consumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 开启发现服务功能
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerUser03Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUser03Application.class, args);
    }

    // 使用负载均衡机制
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
