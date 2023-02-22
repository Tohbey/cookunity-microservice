package com.example.cookunityuserservice.repository;

import com.example.cookunityuserservice.model.Address;
import com.example.cookunityuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressesByUser(User user);

    Optional<Address> findAddressByAddressAndUser(String address, User user);
}
