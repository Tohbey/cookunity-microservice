package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.model.SecretQuestion;

import java.util.Optional;
import java.util.UUID;

public interface RememberTokenService {
    Optional<RememberTokenDTO> getRememberToken(UUID id);

    RememberTokenDTO saveToken(RememberToken rememberToken) throws Exception;

    void deleteToken(UUID id);
}
