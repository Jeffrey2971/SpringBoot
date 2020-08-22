package com.jeffrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// @ImportResource(locations = {"classpath:beans.xml"})
public class SpringBootConfig03Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfig03Application.class, args);
    }

}
