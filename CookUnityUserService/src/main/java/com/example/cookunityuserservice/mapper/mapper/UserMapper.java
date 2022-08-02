package com.example.cookunityuserservice.mapper.mapper;

import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
