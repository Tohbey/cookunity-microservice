package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.service.RememberTokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RememberTokenImpl implements RememberTokenService {
    @Override
    public Optional<RememberTokenDTO> getRememberToken(UUID id) {
        return Optional.empty();
    }

    @Override
    public RememberTokenDTO saveToken(RememberToken rememberToken) {
        return null;
    }

    @Override
    public void deleteToken(UUID id) {

    }
}
