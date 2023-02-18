package com.ironhack.demosecurityjwt.dtos;

import com.ironhack.demosecurityjwt.models.Money.Money;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long idAccountReciever;
    private Long idAccountSender;
    private LocalDateTime dateTime;
    private double amount;
}
