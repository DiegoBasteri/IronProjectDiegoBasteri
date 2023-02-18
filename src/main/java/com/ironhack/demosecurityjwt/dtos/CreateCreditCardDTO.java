package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateCreditCardDTO {
    private String username;
    private String password;
    private String name;
    private Long accountID;
    private LocalDate dateOfBirth;
    private AddressDTO addressDTO;
    private double balance;
    private double creditLimit;
    private double interestRate;
}
