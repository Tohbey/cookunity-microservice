package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.AddressDTO;
import com.example.cookunityuserservice.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService {
    List<AddressDTO> getAddressesForCurrentUser();

    Optional<AddressDTO> getAddress(UUID id);

    void deleteAddress(UUID id);

    Optional<AddressDTO> updateAddress(Address address,UUID id);

    AddressDTO saveAddress(Address address);
}
