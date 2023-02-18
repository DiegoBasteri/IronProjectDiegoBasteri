package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.dtos.TransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionResponseDTO;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.services.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    @PostMapping("/newTransaction")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionResponseDTO newTransaction(@RequestBody TransactionDTO transactionDTO, Authentication authentication){
        System.out.println(authentication.getName());
        return transactionService.createTransaction(transactionDTO);

    }
}
