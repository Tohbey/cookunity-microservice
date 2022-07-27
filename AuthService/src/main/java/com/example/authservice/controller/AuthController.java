package com.example.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {
    public static final String BASE_URL = "/api/v1/auth";

    @GetMapping("/status")
    public String status(){
        return "Auth Controller is working";
    }
}
