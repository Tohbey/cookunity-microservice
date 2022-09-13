package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.dtos.*;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AuthenticationService {

    AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;

    Optional<UserDTO> verifyUser(VerificationRequest verificationRequest) throws Exception;

    Boolean checkIfValidOldPassword(User user, String oldPassword);

    UserDTO changePassword(ForgotPasswordRequest forgotPasswordRequest) throws Exception;

    UserDTO recover(RecoverRequest recoverRequest);

    Optional<User> reset(String email, String token) throws Exception;

    UserDTO resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception;

    Optional<User> getCurrentUser();
}
