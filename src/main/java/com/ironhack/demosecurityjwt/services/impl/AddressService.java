package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * This is a simple method to create a new address.
     * @param address
     * @return
     */
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
