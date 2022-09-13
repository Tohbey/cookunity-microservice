package com.example.cookunityuserservice.dtos;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String token;
    private String password;
}
