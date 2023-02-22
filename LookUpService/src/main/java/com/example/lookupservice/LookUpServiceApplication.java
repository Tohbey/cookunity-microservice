package com.example.lookupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LookUpServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LookUpServiceApplication.class, args);
    }

}
