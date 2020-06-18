package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EgoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgoRestApplication.class, args);
    }

}
