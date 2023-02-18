package com.ironhack.demosecurityjwt;

import com.ironhack.demosecurityjwt.dtos.DeleteResponseDTO;
import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.services.impl.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AddressControllerTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void createAddressTest() {
        Address address = new Address("Street", "City", "Country", 12345);
        Address savedAddress = addressService.createAddress(address);
        assertNotNull(savedAddress);
        assertEquals(address.getCity(), savedAddress.getCity());
        assertEquals(address.getCountry(), savedAddress.getCountry());
        assertEquals(address.getZipCode(), savedAddress.getZipCode());
    }

}
