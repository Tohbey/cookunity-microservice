package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.model.SecretQuestion;

import java.util.Optional;

public interface RememberTokenService {
    Optional<RememberTokenDTO> getRememberToken(Long id);

    RememberTokenDTO saveToken(RememberToken rememberToken) throws Exception;

    void deleteToken(Long id) throws Exception;

    Optional<RememberToken> getRememberTokenByToken(String token);
}
