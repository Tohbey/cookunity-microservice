package com.example.cookunityuserservice.controller;

import com.example.cookunityuserservice.dtos.ResponseObject;
import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.model.SecretQuestion;
import com.example.cookunityuserservice.resource.General;
import com.example.cookunityuserservice.service.SecretQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(SecretQuestionController.BASE_URL)
public class SecretQuestionController {
    public static final String BASE_URL = "/api/v1/secret-question";

    private final SecretQuestionService secretQuestionService;

    public SecretQuestionController(SecretQuestionService secretQuestionService){
        this.secretQuestionService = secretQuestionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<SecretQuestionDTO>> getSecretQuestions(){
        ResponseObject<SecretQuestionDTO> object = new ResponseObject<>();
        try {
            List<SecretQuestionDTO> questions = secretQuestionService.getAllSecretQuestion();
            object.setData(questions);
            object.setMessage(General.RETRIEVED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<SecretQuestionDTO>> getSecretQuestion(@PathVariable UUID id){
        ResponseObject<SecretQuestionDTO> object = new ResponseObject<>();
        try {
            Optional<SecretQuestionDTO> questions = secretQuestionService.getSecretQuestion(id);
            object.setData(Collections.singletonList(questions.get()));
            object.setMessage(General.RETRIEVED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<SecretQuestionDTO>> saveSecretQuestion(@RequestBody SecretQuestion secretQuestion){
        ResponseObject<SecretQuestionDTO> object = new ResponseObject<>();
        try {
            SecretQuestionDTO questionDTO = secretQuestionService.saveSecretQuestion(secretQuestion);
            object.setData(Collections.singletonList(questionDTO));
            object.setMessage(General.CREATED);
            object.setValid(true);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
