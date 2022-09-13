package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.config.CustomDetail;
import com.example.cookunityuserservice.config.CustomDetailService;
import com.example.cookunityuserservice.dtos.*;
import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.jwt.JwtUtils;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.mapper.mapper.UserMapper;
import com.example.cookunityuserservice.model.RememberToken;
import com.example.cookunityuserservice.model.User;
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

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final CustomDetailService customDetailService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RememberTokenService rememberTokenService;
    private final JwtUtils jwtTokenUtil;

    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserMapper userMapper, AuthenticationManager authenticationManager,
                                     CustomDetailService customDetailService, RememberTokenService rememberTokenService, JwtUtils jwtTokenUtil,
                                     UserService userService){
        this.passwordEncoder = passwordEncoder;
        this.customDetailService = customDetailService;
        this.userService = userService;
        this.rememberTokenService = rememberTokenService;
        this.userMapper = userMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
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
    public UserDTO recover(RecoverRequest recoverRequest) {
        return null;
    }

    @Override
    public Optional<User> reset(String email, String token) throws Exception {
        return Optional.empty();
    }

    @Override
    public UserDTO resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception {
        return null;
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
