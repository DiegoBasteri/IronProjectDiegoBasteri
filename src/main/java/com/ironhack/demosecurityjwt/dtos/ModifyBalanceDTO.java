package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;

@Data
public class ModifyBalanceDTO {
    private Long accountId;
    private double amount;
}
