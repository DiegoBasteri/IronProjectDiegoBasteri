package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ThirdPartyTransactionDTO {
    private Long idAccountReciever;
    private Long idAccountSender;
    private double amount;
    private String hashedKey;

}
