package com.example.cookunityuserservice.service.impl;

import com.example.cookunityuserservice.exceptions.NotFoundException;
import com.example.cookunityuserservice.mapper.DTO.AddressDTO;
import com.example.cookunityuserservice.mapper.mapper.AddressMapper;
import com.example.cookunityuserservice.model.Address;
import com.example.cookunityuserservice.model.User;
import com.example.cookunityuserservice.repository.AddressRepository;
import com.example.cookunityuserservice.repository.UserRepository;
import com.example.cookunityuserservice.service.AddressService;
import com.example.cookunityuserservice.service.AuthenticationService;
import com.example.cookunityuserservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.Long;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    private final UserService userService;

    private final AuthenticationService authenticationService;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper, UserService userService, AuthenticationService authenticationService){
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }
    @Override
    public List<AddressDTO> getAddressesForCurrentUser(Long userId) {
        Optional<User> user = this.userService.getUser(userId);

        List<Address> addresses = this.addressRepository.findAddressesByUser(user.get());
        return addresses.stream().map(
                address -> {
                    AddressDTO addressDTO = addressMapper.addressToAddressDTO(address);
                    addressDTO.setUserId(address.getUser().getId());

                    return addressDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<AddressDTO> getAddress(Long id) {
        Optional<Address> address = findAddress(id);

        Optional<AddressDTO> addressDTO = address.map(addressMapper::addressToAddressDTO)
                .map(addressDTO1 -> {
                    addressDTO1.setUserId(address.get().getUser().getId());

                    return addressDTO1;
                });
        return addressDTO;
    }

    @Override
    public void deleteAddress(Long id) {
        findAddress(id);
        this.addressRepository.deleteById(id);
    }

    @Override
    public Optional<AddressDTO> updateAddress(Address address, Long id) {
        Optional<Address> currentAddress = findAddress(id);
        return currentAddress.map(address1 -> {
            if(address.getAddress() != null){
                address1.setAddress(address.getAddress());
            }

            if(address.getCountry() != null){
                address1.setCountry(address.getCountry());
            }

            if(address.getLGA() != null){
                address1.setLGA(address.getLGA());
            }

            if(address.getIsCurrent() != null){
                address1.setIsCurrent(address.getIsCurrent());
            }

            if(address.getState() != null){
                address1.setState(address.getState());
            }
            return saveAndReturnDTO(address1);
        });
    }

    @Override
    public AddressDTO saveAddress(Address address) throws Exception {
        Optional<User> currentUser = this.authenticationService.getCurrentUser();

        address.setUser(currentUser.get());
        Optional<Address> checkAddress = this.addressRepository.findAddressByAddress(address.getAddress());
        if(checkAddress.isPresent()){
            throw new Exception("Duplicate Address found");
        }

        Address savedAddress = this.addressRepository.save(address);

        AddressDTO addressDTO = addressMapper.addressToAddressDTO(savedAddress);
        addressDTO.setUserId(savedAddress.getUser().getId());
        return addressDTO;
    }

    private AddressDTO saveAndReturnDTO(Address address){
        Address savedAddress = this.addressRepository.save(address);

        AddressDTO addressDTO = addressMapper.addressToAddressDTO(savedAddress);
        addressDTO.setUserId(savedAddress.getUser().getId());

        return addressDTO;
    }

    private Optional<Address> findAddress(Long id){
        Optional<Address> currentAddress = this.addressRepository.findById(id);
        if(currentAddress.isEmpty()){
            throw new NotFoundException("Address Not Found. for ID value " + id);
        }

        return currentAddress;
    }
}
