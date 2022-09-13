package com.example.cookunityuserservice.dtos;

import lombok.Data;

@Data
public class VerificationRequest {
    private String email;
    private String token;
}
