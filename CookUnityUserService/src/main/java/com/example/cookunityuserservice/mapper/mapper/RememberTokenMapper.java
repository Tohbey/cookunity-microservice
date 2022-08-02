package com.example.cookunityuserservice.mapper.mapper;

import com.example.cookunityuserservice.mapper.DTO.RememberTokenDTO;
import com.example.cookunityuserservice.model.RememberToken;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RememberTokenMapper {
    RememberTokenMapper INSTANCE = Mappers.getMapper(RememberTokenMapper.class);

    RememberTokenDTO rememberTokenToRememberTokenDTO(RememberToken rememberToken);

    RememberToken rememberTokenDTOToRememberToken(RememberTokenDTO rememberTokenDTO);
}
