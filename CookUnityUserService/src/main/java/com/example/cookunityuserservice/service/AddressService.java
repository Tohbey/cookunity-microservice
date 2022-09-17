package com.example.cookunityuserservice.service;

import com.example.cookunityuserservice.mapper.DTO.AddressDTO;
import com.example.cookunityuserservice.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<AddressDTO> getAddressesForCurrentUser(Long userId);

    Optional<AddressDTO> getAddress(Long id);

    void deleteAddress(Long id);

    Optional<AddressDTO> updateAddress(Address address,Long id);

    AddressDTO saveAddress(Address address) throws Exception;
}
