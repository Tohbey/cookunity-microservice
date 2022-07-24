package com.example.cookunitydiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CookUnityDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookUnityDiscoveryServiceApplication.class, args);
    }

}
