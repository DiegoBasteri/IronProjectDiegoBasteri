package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBankUserDTO {
    private String name;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private AddressDTO addressDTO;
    private AddressDTO mailingAddress;
}
