package com.example.cookunitytransactionservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TransactionController.BASE_URL)
public class TransactionController {
    public static final String BASE_URL = "/api/v1/transaction";

    @GetMapping("/status")
    public String status(){
        return "Transaction Controller is working";
    }
}
