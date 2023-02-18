package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.services.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdressController {

    @Autowired
    AddressService addressService;


    @PostMapping("/newAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@RequestBody Address address) {
        return addressService.createAddress(address);
    }
}
