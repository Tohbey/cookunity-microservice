package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.model.SecretQuestion;
import com.example.cookunityuserservice.repository.SecretQuestionRepository;
import com.example.cookunityuserservice.service.SecretQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SecretQuestionServiceImpl implements SecretQuestionService{
    private final SecretQuestionRepository secretQuestionRepository;

    public SecretQuestionServiceImpl(SecretQuestionRepository secretQuestionRepository){
        this.secretQuestionRepository = secretQuestionRepository;
    }
    @Override
    public List<SecretQuestionDTO> getAllSecretQuestion() {
        List<SecretQuestion> questions = this.secretQuestionRepository.findAll();
        return questions.stream().map(
                question -> {
                    SecretQuestionDTO secretQuestionDTO = new SecretQuestionDTO();
                    secretQuestionDTO.setQuestion(question.getQuestion());
                    secretQuestionDTO.setId(question.getId());

                    return secretQuestionDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<SecretQuestionDTO> getSecretQuestion(UUID id) {
        Optional<SecretQuestion> secretQuestion = this.secretQuestionRepository.findById(id);
        if(secretQuestion.isEmpty()){
            throw new NotFoundException("Secret Question not found");
        }

        SecretQuestionDTO secretQuestionDTO = new SecretQuestionDTO();
        secretQuestionDTO.setQuestion(secretQuestion.get().getQuestion());
        secretQuestionDTO.setId(secretQuestion.get().getId());

        return Optional.of(secretQuestionDTO);
    }

    @Override
    public SecretQuestionDTO saveSecretQuestion(SecretQuestion secretQuestion) throws Exception {
        Optional<SecretQuestion> checkQuestion = this.secretQuestionRepository.findSecretQuestionByQuestion(secretQuestion.getQuestion());
        if(checkQuestion.isPresent()){
            throw new Exception("this secret question already exist");
        }
        return saveAndReturnDTO(secretQuestion);
    }

    private SecretQuestionDTO saveAndReturnDTO(SecretQuestion secretQuestion){
        SecretQuestion savedQuestion = this.secretQuestionRepository.save(secretQuestion);

        SecretQuestionDTO secretQuestionDTO = new SecretQuestionDTO();
        secretQuestionDTO.setQuestion(savedQuestion.getQuestion());
        secretQuestionDTO.setId(secretQuestionDTO.getId());

        return secretQuestionDTO;
    }
}
