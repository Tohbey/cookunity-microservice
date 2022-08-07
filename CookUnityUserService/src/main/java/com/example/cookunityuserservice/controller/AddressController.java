package com.example.cookunityuserservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AddressController.BASE_URL)
public class AddressController {
    public static final String BASE_URL = "/api/v1/address";
}
