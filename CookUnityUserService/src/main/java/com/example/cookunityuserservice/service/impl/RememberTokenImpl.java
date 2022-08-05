package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.mapper.DTO.SecretQuestionDTO;
import com.example.cookunityuserservice.mapper.mapper.RememberTokenMapper;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.model.SecretQuestion;
import com.example.cookunityuserservice.repository.RememberTokenRepository;
import com.example.cookunityuserservice.service.RememberTokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RememberTokenImpl implements RememberTokenService {
    private final RememberTokenRepository rememberTokenRepository;
    private final RememberTokenMapper rememberTokenMapper;
    public RememberTokenImpl(RememberTokenRepository rememberTokenRepository, RememberTokenMapper rememberTokenMapper){
        this.rememberTokenRepository = rememberTokenRepository;
        this.rememberTokenMapper = rememberTokenMapper;
    }
    @Override
    public Optional<RememberTokenDTO> getRememberToken(UUID id) {
        Optional<RememberToken> rememberToken = this.rememberTokenRepository.findById(id);
        if(rememberToken.isEmpty()){
            throw new NotFoundException("token not found");
        }

        return rememberToken.map(rememberTokenMapper::rememberTokenToRememberTokenDTO)
                .map(tokenDTO -> {
                    tokenDTO.setUserURL(rememberToken.get().getUser().getId().toString());

                    return tokenDTO;
                });
    }

    @Override
    public RememberTokenDTO saveToken(RememberToken rememberToken) throws Exception {
        Optional<RememberToken> checkToken = this.rememberTokenRepository.findRememberTokenByToken(rememberToken.getToken());
        if(checkToken.isPresent()){
            throw new Exception("Token already exist");
        }
        return saveAndReturnDTO(rememberToken);
    }

    private RememberTokenDTO saveAndReturnDTO(RememberToken rememberToken){
        RememberToken savedToken = this.rememberTokenRepository.save(rememberToken);

        RememberTokenDTO returnDTO = this.rememberTokenMapper.rememberTokenToRememberTokenDTO(savedToken);
        returnDTO.setUserURL(savedToken.getUser().getId().toString());

        return returnDTO;
    }

    @Override
    public void deleteToken(UUID id) throws Exception {
        Optional<RememberToken> checkToken = this.rememberTokenRepository.findById(id);
        if(checkToken.isEmpty()){
            throw new Exception("Token not found");
        }
        this.rememberTokenRepository.deleteById(id);
    }
}
