package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.model.User;

import java.util.List;
import java.util.Optional;
import java.lang.Long;

public interface UserService {

    List<UserDTO> getAllUser();

    Optional<UserDTO> getUserDTO(Long id);

    Optional<User> getUser(Long id);

    Optional<User> getUserByEmail(String email);

    UserDTO saveUser(UserDTO userDTO) throws Exception;

    String getUserUrl(Long id);

    String returnUserFullName(User user);
    void deleteUser(Long id) throws Exception;

    Optional<UserDTO> updateUser(User user, Long id);

    String generateRandomToken(int length);
}
