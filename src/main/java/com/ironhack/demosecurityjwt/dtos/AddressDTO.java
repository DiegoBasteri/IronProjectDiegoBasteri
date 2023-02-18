package com.ironhack.demosecurityjwt.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String country;
    private String city;
    @Column(length = 5)
    private Integer zipCode;
}
