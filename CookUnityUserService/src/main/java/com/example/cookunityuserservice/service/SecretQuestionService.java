package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.model.SecretQuestion;

import java.util.List;
import java.util.Optional;

public interface SecretQuestionService {
    List<SecretQuestionDTO> getAllSecretQuestion();

    Optional<SecretQuestionDTO> getSecretQuestion(Long id);

    SecretQuestionDTO saveSecretQuestion(SecretQuestion secretQuestion) throws Exception;
}
