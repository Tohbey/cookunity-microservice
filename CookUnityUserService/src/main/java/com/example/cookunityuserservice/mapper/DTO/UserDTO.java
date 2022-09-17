package com.example.cookunityuserservice.mapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private SecretQuestionDTO secretQuestionDTO;
    private String role;
    private RememberTokenDTO token;
    private String profile;
    private String currentAddress;
    private AddressListDTO addresses;
    private Date createdAt;
    private Date updatedAt;

    @JsonProperty("user_url")
    private String userUrl;
}
