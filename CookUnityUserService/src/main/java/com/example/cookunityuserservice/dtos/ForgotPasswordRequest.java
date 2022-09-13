package com.example.cookunityuserservice.dtos;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
