package com.example.cookunityuserservice.mapper.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressListDTO {
    List<AddressDTO> addresses;
}
