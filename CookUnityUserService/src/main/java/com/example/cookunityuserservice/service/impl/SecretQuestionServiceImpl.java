package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.model.SecretQuestion;
import com.example.cookunityuserservice.service.SecretQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecretQuestionServiceImpl implements SecretQuestionService{
    @Override
    public List<SecretQuestionDTO> getAllSecretQuestion() {
        return null;
    }

    @Override
    public Optional<SecretQuestionDTO> getSecretQuestion(UUID id) {
        return Optional.empty();
    }

    @Override
    public SecretQuestionDTO saveSecretQuestion(SecretQuestion secretQuestion) {
        return null;
    }
}
