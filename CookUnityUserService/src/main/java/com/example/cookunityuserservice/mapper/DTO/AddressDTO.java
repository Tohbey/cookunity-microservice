package com.example.cookunityuserservice.mapper.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressDTO {
    private UUID id;
    private String country;
    private String state;
    private String LGA;
    private String address;
    private Boolean isCurrent;
}
