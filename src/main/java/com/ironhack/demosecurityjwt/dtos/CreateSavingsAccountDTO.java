package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateSavingsAccountDTO {
    private String username;
    private String password;
    private String name;
    private Long id;
    private double balance;
    private double minimumBalance;
    private LocalDate dateOfBirth;
    private AddressDTO addressDTO;
    private BigDecimal interestRate;
}
