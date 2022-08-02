package com.example.cookunityuserservice.mapper.mapper;

import com.example.cookunityuserservice.mapper.DTO.AddressDTO;
import com.example.cookunityuserservice.mapper.DTO.UserDTO;
import com.example.cookunityuserservice.model.Address;
import com.example.cookunityuserservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDTO(Address address);

    Address addressDTOToUAddress(AddressDTO addressDTO);
}
