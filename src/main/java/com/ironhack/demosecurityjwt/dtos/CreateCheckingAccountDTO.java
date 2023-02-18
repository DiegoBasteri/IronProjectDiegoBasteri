package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateCheckingAccountDTO {
    private String username;
    private String password;
    private String name;
    private double balance;
    private LocalDate dateOfBirth;
    private AddressDTO addressDTO;
    private double creditLimit;
}

