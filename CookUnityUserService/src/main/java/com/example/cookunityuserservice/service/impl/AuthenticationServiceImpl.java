package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.config.CustomDetail;
import com.example.cookunityuserservice.config.CustomDetailService;
import com.example.cookunityuserservice.dtos.*;
import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.jwt.JwtUtils;
import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.mapper.mapper.RememberTokenMapper;
import com.example.cookunityuserservice.mapper.mapper.UserMapper;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.repository.UserRepository;
import com.example.cookunityuserservice.service.AuthenticationService;
import com.example.cookunityuserservice.service.RememberTokenService;
import com.example.cookunityuserservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RememberTokenMapper rememberTokenMapper;
    private final CustomDetailService customDetailService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RememberTokenService rememberTokenService;

    private final UserRepository userRepository;
    private final JwtUtils jwtTokenUtil;

    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserMapper userMapper, AuthenticationManager authenticationManager,
                                     CustomDetailService customDetailService, RememberTokenService rememberTokenService, JwtUtils jwtTokenUtil,
                                     UserService userService, RememberTokenMapper rememberTokenMapper,
                                     UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.customDetailService = customDetailService;
        this.userService = userService;
        this.rememberTokenService = rememberTokenService;
        this.userMapper = userMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rememberTokenMapper = rememberTokenMapper;
    }


    @Override
    public AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        this.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final CustomDetail customDetail = customDetailService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(customDetail);

        return new AuthenticationResponse(token);
    }

    @Override
    public Optional<UserDTO> verifyUser(VerificationRequest verificationRequest) throws Exception {
        Optional<User> user = userService.getUserByEmail(verificationRequest.getEmail());

        //find token
        Optional<RememberToken> rememberToken = rememberTokenService.getRememberTokenByToken(verificationRequest.getToken());

        //validate the token
        if (!user.get().getToken().getToken().equals(rememberToken.get().getToken())) {
            throw new Exception("Incorrect Token");
        }

        //update user
        user.get().setIsActive(1);
        user.get().setToken(null);

        rememberTokenService.deleteToken(rememberToken.get().getId());

        return userService.updateUser(user.get(), user.get().getId());
    }

    @Override
    public Boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public UserDTO changePassword(ForgotPasswordRequest forgotPasswordRequest) throws Exception {
        Optional<User> user = getCurrentUser();

        if (!checkIfValidOldPassword(user.get(), forgotPasswordRequest.getOldPassword())) {
            throw new Exception("Invalid Old Password");
        }

        String pwd = forgotPasswordRequest.getNewPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.get().setPassword(encryptPwd);

        Optional<UserDTO> updatedUser = userService.updateUser(user.get(), user.get().getId());

        return updatedUser.get();
    }

    @Override
    public UserDTO recover(RecoverRequest recoverRequest) throws Exception {
        Optional<User> user = userService.getUserByEmail(recoverRequest.getEmail());
        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for EMAIL value " + recoverRequest.getEmail());
        }

        String token = userService.generateRandomToken(20);
        RememberToken passwordRetrieve = new RememberToken();
        passwordRetrieve.setToken(token);

        //adding 20 minutes to the current time
        Calendar present = Calendar.getInstance();
        long timeInSecs = present.getTimeInMillis();
        Date expiredAt = new Date(timeInSecs + (20 * 60 * 1000));

        //save token
        passwordRetrieve.setExpiredAt(expiredAt);
        RememberTokenDTO savedPasswordRetrieveTokenDTO = rememberTokenService.saveToken(passwordRetrieve);

        //password retrieve token dto
        savedPasswordRetrieveTokenDTO.setUserURL(this.userService.getUserUrl(user.get().getId()));

        user.get().setToken(this.rememberTokenMapper.rememberTokenDTOToRememberToken(savedPasswordRetrieveTokenDTO));

        Optional<UserDTO> returnDTO = userService.updateUser(user.get(), user.get().getId());

        returnDTO.get().setUserUrl(this.userService.getUserUrl(user.get().getId()));
        returnDTO.get().setToken(savedPasswordRetrieveTokenDTO);

        return returnDTO.get();
    }

    @Override
    public Optional<User> reset(String email, String token) throws Exception {
        Optional<User> user = this.userService.getUserByEmail(email);
        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for EMAIL value " + email);
        }

        Optional<RememberToken> passwordRetrieve = this.rememberTokenService.getRememberTokenByToken(token);
        if (passwordRetrieve.isEmpty()) {
            throw new NotFoundException("Token Not Found. for TOKEN value " + token);
        }

        if (!user.get().getToken().getToken().equals(passwordRetrieve.get().getToken())) {
            throw new Exception("Incorrect Token");
        }

        return user;
    }

    @Override
    public UserDTO resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception {
        Optional<User> user = reset(resetPasswordRequest.getEmail(), resetPasswordRequest.getToken());

        String encryptPwd = passwordEncoder.encode(resetPasswordRequest.getPassword());
        user.get().setPassword(encryptPwd);

        Optional<RememberToken> passwordRetrieve = this.rememberTokenService.getRememberTokenByToken(resetPasswordRequest.getToken());
        if (passwordRetrieve.isEmpty()) {
            throw new NotFoundException("Token Not Found. for TOKEN value " + resetPasswordRequest.getToken());
        }
        this.rememberTokenService.deleteToken(passwordRetrieve.get().getId());

        user.get().setToken(null);
        User savedUser = user.map(user1 -> {
            user1.setPassword(user.get().getPassword());

            return this.userRepository.save(user1);
        }).orElseGet(() -> {
            return userRepository.save(user.get());
        });

        UserDTO userDTO = userMapper.userToUserDTO(savedUser);
        userDTO.setUserUrl(this.userService.getUserUrl(userDTO.getId()));

        return userDTO;
    }

    @Override
    public Optional<User> getCurrentUser() {
        CustomDetail userDetail = (CustomDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetail.getUsername();
        Optional<User> user = userService.getUserByEmail(email);

        return user;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
