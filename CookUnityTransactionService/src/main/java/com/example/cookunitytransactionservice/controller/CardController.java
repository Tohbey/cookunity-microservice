package com.example.cookunitytransactionservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CardController.BASE_URL)
public class CardController {

    public static final String BASE_URL = "/api/v1/card";

}
