package com.example.cookunityuserservice.mapper.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class RememberTokenDTO {
    private Long id;
    private String token;
    private Date expiredAt;

    @JsonProperty("user_url")
    private String userURL;
}
