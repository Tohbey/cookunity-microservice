package com.example.cookunityuserservice.controller;

import com.example.cookunityuserservice.dtos.ResponseObject;
import com.example.cookunityuserservice.mapper.DTO.AddressDTO;
import com.example.cookunityuserservice.mapper.DTO.AddressListDTO;
import com.example.cookunityuserservice.model.Address;
import com.example.cookunityuserservice.resource.General;
import com.example.cookunityuserservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(AddressController.BASE_URL)
public class AddressController {
    public static final String BASE_URL = "/api/v1/address";
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<AddressListDTO>> getAddressesByUser(UUID userId){
        ResponseObject<AddressListDTO> object = new ResponseObject<>();
        try {
            List<AddressDTO> addresses = addressService.getAddressesForCurrentUser(userId);
            object.setData(Collections.singletonList(new AddressListDTO(addresses)));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<AddressDTO>> getAddressById(@PathVariable UUID addressId){
        ResponseObject<AddressDTO> object = new ResponseObject<>();
        try {
            Optional<AddressDTO> address = addressService.getAddress(addressId);
            object.setData(Collections.singletonList(address.get()));
            object.setValid(true);
            object.setMessage(General.RETRIEVED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseObject> deleteAddress(@PathVariable UUID addressId){
        ResponseObject object = new ResponseObject();
        try {
            addressService.deleteAddress(addressId);
            object.setValid(true);
            object.setMessage(General.DELETED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<AddressDTO>> createAddress(@RequestBody Address address){
        ResponseObject<AddressDTO> object = new ResponseObject<>();
        try {
            AddressDTO addressDTO = addressService.saveAddress(address);
            object.setData(Collections.singletonList(addressDTO));
            object.setValid(true);
            object.setMessage(General.CREATED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObject<AddressDTO>> updateAddress(@RequestBody Address address, @PathVariable UUID id){
        ResponseObject<AddressDTO> object = new ResponseObject<>();
        try {
            Optional<AddressDTO> addressDTO = addressService.updateAddress(address, id);
            object.setData(Collections.singletonList(addressDTO.get()));
            object.setValid(true);
            object.setMessage(General.UPDATED);
        }catch (Exception e){
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }
}
