package com.jeffrey.springbootactuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {


        if (false) {
            // 返回健康状态
            return Health.up().build();
        } else {
            // 自定义的检查方法
            return Health.down().withDetail("msg", "服务异常").build();
        }


    }
}
