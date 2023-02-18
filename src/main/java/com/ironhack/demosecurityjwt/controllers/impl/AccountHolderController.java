package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.dtos.AccountHolderDTO;

import com.ironhack.demosecurityjwt.dtos.TransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionResponseDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.repositories.BaseCheckingAccountRepository;
import com.ironhack.demosecurityjwt.services.impl.AccountHolderService;
import com.ironhack.demosecurityjwt.services.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;
    @Autowired
    BaseCheckingAccountRepository baseCheckingAccounts;
    @Autowired
    TransactionService transactionService;


    @GetMapping("/Accounts/ownAccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<BaseCheckingAccount> baseCheckingAccounts(Authentication authentication){
        System.out.println(authentication.getPrincipal());
        return accountHolderService.findByPrimaryOwnerAccounts(authentication);
    }

    @PostMapping("/AccountHolder/newTransaction")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionResponseDTO newTransaction(@RequestBody TransactionDTO transactionDTO){
        return transactionService.createTransaction(transactionDTO);
    }












}

