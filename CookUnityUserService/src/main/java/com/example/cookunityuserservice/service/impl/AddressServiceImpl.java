package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.mapper.DTO.AddressDTO;
import com.example.cookunityuserservice.model.Address;
import com.example.cookunityuserservice.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public List<AddressDTO> getAddressesForCurrentUser() {
        return null;
    }

    @Override
    public Optional<AddressDTO> getAddress(UUID id) {
        return Optional.empty();
    }

    @Override
    public void deleteAddress(UUID id) {

    }

    @Override
    public Optional<AddressDTO> updateAddress(Address address, UUID id) {
        return Optional.empty();
    }

    @Override
    public AddressDTO saveAddress(Address address) {
        return null;
    }
}
