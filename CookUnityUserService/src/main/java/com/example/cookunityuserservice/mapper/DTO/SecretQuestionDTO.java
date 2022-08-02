package com.example.cookunityuserservice.mapper.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class SecretQuestionDTO {
    private UUID id;
    private String question;
}
