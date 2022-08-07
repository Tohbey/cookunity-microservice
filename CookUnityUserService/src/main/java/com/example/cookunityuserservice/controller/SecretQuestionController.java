package com.example.cookunityuserservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecretQuestionController.BASE_URL)
public class SecretQuestionController {
    public static final String BASE_URL = "/api/v1/secret-question";
}
