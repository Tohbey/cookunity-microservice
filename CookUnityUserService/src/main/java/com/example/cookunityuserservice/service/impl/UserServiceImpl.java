package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.controller.UserController;
import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.mapper.mapper.UserMapper;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.repository.UserRepository;
import com.example.cookunityuserservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(
                user -> {
                    UserDTO userDTO = userMapper.userToUserDTO(user);
                    userDTO.setFullName(returnUserFullName(user));
                    userDTO.setUserUrl(getUserUrl(user.getId()));

                    return userDTO;
                }
        ).collect(Collectors.toList());
    }

    private String getUserUrl(UUID id) {
        return UserController.BASE_URL + "/" + id;
    }

    private String returnUserFullName(User user) {
        return user.getLastName() + " " + user.getOtherNames();
    }
    @Override
    public Optional<UserDTO> getUser(UUID id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value " + id);
        }

        Optional<UserDTO> userDto = user.map(userMapper::userToUserDTO)
                .map(userDTO -> {
                    userDTO.setUserUrl(getUserUrl(user.get().getId()));
                    userDTO.setFullName(returnUserFullName(user.get()));
                    return userDTO;
                });

        return userDto;
    }

    @Override
    public UserDTO saveUser(User user) throws Exception {
        Optional<User> checkUser = this.userRepository.findUserByEmail(user.getEmail());
        if (checkUser.isPresent()) {
            throw new Exception("A user with this email already exist " + checkUser.get().getEmail());
        }

        User savedUser = this.userRepository.save(user);
        return null;
    }

    @Override
    public void deleteUser(UUID id) throws Exception {
        Optional<User> checkUser = this.userRepository.findById(id);
        if (checkUser.isPresent()) {
            throw new Exception("A user with this email already exist " + checkUser.get().getEmail());
        }

        this.userRepository.deleteById(id);
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
