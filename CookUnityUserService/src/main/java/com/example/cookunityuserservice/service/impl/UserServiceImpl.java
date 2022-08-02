package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> getAllUser() {
        return null;
    }

    @Override
    public Optional<UserDTO> getUser(UUID id) {
        return Optional.empty();
    }

    @Override
    public UserDTO saveUser(User user) throws Exception {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public Optional<UserDTO> updateUser(User user, UUID id) {
        return Optional.empty();
    }

    @Override
    public String generateRandomToken(int length) {
        return null;
    }
}
