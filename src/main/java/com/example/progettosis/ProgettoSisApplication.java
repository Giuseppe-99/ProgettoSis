package com.example.progettosis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude={
        SecurityAutoConfiguration.class})
@Configuration


public class ProgettoSisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgettoSisApplication.class, args);
    }

}
