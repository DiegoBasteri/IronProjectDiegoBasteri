package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.Enums.AdminModifyBalanceType;
import com.ironhack.demosecurityjwt.dtos.*;
import com.ironhack.demosecurityjwt.models.Accounts.*;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankRoles.ThirdParty;
import com.ironhack.demosecurityjwt.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Accounts")
public class AdminController {
    @Autowired
    CheckingAccountService checkingAccountService;
    @Autowired
    SavingAccountService savingAccountService;
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    CreditCardService creditCardService;
    @Autowired
    AccountHolderService accountHolderService;


    @GetMapping("/CheckingAccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CheckingAccount> accountList(){
        return checkingAccountService.getAllCheckingAccounts();
    }

    @GetMapping("/StudentAccounts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StudentCheckingAccount> studentList(){
        return checkingAccountService.getAllStudentAccounts();
    }

    @GetMapping("/Savings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Savings> savingsList(){
        return savingAccountService.savingsList();
    }

    @GetMapping("/AccountHolder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<AccountHolder> accountHolders(){
        return accountHolderService.accountHolders();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCheckingAccountResponseDTO createCheckingAccount (@RequestBody CreateCheckingAccountDTO checkingAccountDTO){
        return checkingAccountService.newAccount(checkingAccountDTO);
    }

    @PostMapping("/createSavings")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCheckingAccountResponseDTO createSavingsAccount(@RequestBody CreateSavingsAccountDTO savingsAccountDTO){
       return savingAccountService.newSavingAccount(savingsAccountDTO);
    }
    @PostMapping("/createSavingAccountOnly")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings newSavingAccount (@RequestBody CreateSavingsAccountDTO savingsAccountDTO){
        return savingAccountService.createSavingsForExistingAccountHolder(savingsAccountDTO);
    }
    @PostMapping("/createCreditCard")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateCheckingAccountResponseDTO createCreditCard(@RequestBody CreateCreditCardDTO creditCardDTO){
        return creditCardService.newCreditCard(creditCardDTO);
    }
    @PutMapping("/createCreditCardOnly")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreditCard createCreditCardOnly(@RequestBody CreateCreditCardDTO creditCardDTO){
        return creditCardService.createCreditCardOnly(creditCardDTO);
    }


    @PostMapping("/createAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public NewBankUserResponseDTO newAccountHolder(@RequestBody CreateCheckingAccountDTO accountHolderDTO){
        return userService.newAccountHolder(accountHolderDTO);
    }

    @PostMapping("/createThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public NewBankUserResponseDTO newThirdParty (@RequestBody CreateThirdPartyAccountDTO thirdPartyAccountDTO){
        return userService.newThirdParty(thirdPartyAccountDTO);
    }

    @GetMapping("/thirdParty")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ThirdParty> thirdPartyList (){
        return userService.getThirdParty();
    }

    @PostMapping("/newTransaction")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionResponseDTO newTransaction(@RequestBody TransactionDTO transactionDTO){
        return transactionService.createTransaction(transactionDTO);

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.GONE)
    public DeleteResponseDTO deleteChekingAccount(@RequestParam Long id){
        return userService.deleteCheckingAccount(id);
    }

    @PatchMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BaseCheckingAccount modifyBalance(@RequestBody ModifyBalanceDTO modifyBalanceDTO, @RequestParam AdminModifyBalanceType modifyType){
        return userService.modifyBalanceOfAccount(modifyBalanceDTO,modifyType);
    }

}


