package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDTO> getAllUser();

    Optional<UserDTO> getUser(UUID id);

    UserDTO saveUser(User user) throws Exception;

    void deleteUser(UUID id) throws Exception;

    Optional<UserDTO> updateUser(User user, UUID id);

    String generateRandomToken(int length);
}
