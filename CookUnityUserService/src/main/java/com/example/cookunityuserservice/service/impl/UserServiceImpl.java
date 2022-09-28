package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.controller.UserController;
import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.mapper.mapper.UserMapper;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.model.SecretQuestion;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.repository.UserRepository;
import com.example.cookunityuserservice.service.RememberTokenService;
import com.example.cookunityuserservice.service.SecretQuestionService;
import com.example.cookunityuserservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final SecretQuestionService secretQuestionService;

    private final RememberTokenService rememberTokenService;

    final String alphabet = "0123456789ABCDE";
    final int N = alphabet.length();

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           RememberTokenService rememberTokenService,
                           SecretQuestionService secretQuestionService){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.rememberTokenService = rememberTokenService;
        this.secretQuestionService = secretQuestionService;
    }
    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(
                user -> {
                    UserDTO userDTO = userMapper.userToUserDTO(user);
                    userDTO.setUserUrl(getUserUrl(user.getId()));

                    return userDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public String getUserUrl(Long id) {
        return UserController.BASE_URL + "/" + id;
    }

    @Override
    public String returnUserFullName(User user) {
        return user.getLastName() + " " + user.getOtherNames();
    }

    @Override
    public Optional<User> getUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value " + id);
        }

        return user;
    }

    @Override
    public Optional<UserDTO> getUserDTO(Long id) {
        Optional<User> user = this.getUser(id);

        Optional<UserDTO> userDto = user.map(userMapper::userToUserDTO)
                .map(userDTO -> {
                    userDTO.setUserUrl(getUserUrl(user.get().getId()));
                    return userDTO;
                });

        return userDto;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = this.userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for email value " +email );
        }
        return user;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws Exception {
        Optional<User> checkUser = this.userRepository.findUserByEmail(userDTO.getEmail());
        if (checkUser.isPresent()) {
            throw new Exception("A user with this email already exist " + checkUser.get().getEmail());
        }
        Optional<SecretQuestion> secretQuestion = this.secretQuestionService.getSecretQuestion(userDTO.getSecretQuestionDTO().getId());

        User user = this.userMapper.userDTOToUser(userDTO);
        user.setPassword(userDTO.getUserPassword());
        user.setSecretQuestion(secretQuestion.get());

        User savedUser = this.userRepository.save(user);

        RememberToken token = generateToken();
        RememberTokenDTO rememberTokenDTO = rememberTokenService.saveToken(token);
        token.setId(rememberTokenDTO.getId());
        savedUser.setToken(token);

        Optional<UserDTO> returnDTO = this.updateUser(savedUser, savedUser.getId());

        if(returnDTO.isPresent()){
            returnDTO.get().setUserUrl(getUserUrl(returnDTO.get().getId()));
            returnDTO.get().setToken(rememberTokenDTO);
            returnDTO.get().setSecretQuestionDTO(this.secretQuestionService.secretQuestionMapToSecretQuestionDTO(secretQuestion.get()));
        }

        return returnDTO.get();
    }

    private RememberToken generateToken(){
        //token save.
        RememberToken token = new RememberToken();
        token.setToken(generateRandomToken(20));

        //adding 20 minutes to the current time
        Calendar present = Calendar.getInstance();
        long timeInSecs = present.getTimeInMillis();
        Date expiredAt = new Date(timeInSecs + (20 * 60 * 1000));

        token.setExpiredAt(expiredAt);

        return token;
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Optional<User> checkUser = this.userRepository.findById(id);
        if (checkUser.isPresent()) {
            throw new Exception("A user with this email already exist " + checkUser.get().getEmail());
        }

        this.userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> updateUser(User user, Long id) {
        Optional<User> currentUser = this.userRepository.findById(id);
        if (currentUser.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value" + id);
        }

        return currentUser.map(user1 -> {
            if (user.getEmail() != null) {
                user1.setEmail(user.getEmail());
            }

            if (user.getLastName() != null) {
                user1.setLastName(user.getLastName());
            }

            if (user.getOtherNames() != null) {
                user1.setOtherNames(user.getOtherNames());
            }

            if (user.getIsActive() == 0 || user.getIsActive() == 1) {
                user1.setIsActive(user.getIsActive());
            }

            return saveAndReturnDTO(user1);
        });
    }

    private UserDTO saveAndReturnDTO(User user) {
        User savedUser = this.userRepository.save(user);

        UserDTO returnDTO = userMapper.userToUserDTO(savedUser);

        returnDTO.setUserUrl(getUserUrl(savedUser.getId()));

        return returnDTO;
    }

    @Override
    public String generateRandomToken(int length) {
        String token = "";

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            String s = token + alphabet.charAt(r.nextInt(N));

            token = s;
        }
        return token;
    }
}
