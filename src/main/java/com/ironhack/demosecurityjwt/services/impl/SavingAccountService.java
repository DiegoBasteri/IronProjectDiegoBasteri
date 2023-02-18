package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.dtos.CreateCheckingAccountResponseDTO;
import com.ironhack.demosecurityjwt.dtos.CreateSavingsAccountDTO;
import com.ironhack.demosecurityjwt.models.Accounts.Savings;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.repositories.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SavingAccountService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    /**
     * This method creates a new saving account from 0 , Creating also a new account holder.
     * @param savingsAccountDTO
     * @return
     */
    public CreateCheckingAccountResponseDTO newSavingAccount (CreateSavingsAccountDTO savingsAccountDTO){
        Address address = new Address(
                savingsAccountDTO.getAddressDTO().getStreet(),
                savingsAccountDTO.getAddressDTO().getCity(),
                savingsAccountDTO.getAddressDTO().getCountry(),
                savingsAccountDTO.getAddressDTO().getZipCode()
        );
        addressRepository.save(address);
        AccountHolder accountHolder = new AccountHolder(
                null,
                savingsAccountDTO.getName(),
                savingsAccountDTO.getUsername(),
                passwordEncoder.encode(savingsAccountDTO.getPassword()),
                new ArrayList<>(),
                savingsAccountDTO.getDateOfBirth(),
                address,
                address
        );

        accountHolder = accountHolderRepository.save(accountHolder);

        Savings savings = new Savings();
        savings.setPrimaryOwner(accountHolder);
        savings.setStatus(Status.ACTIVE);
        savings.setInterestRate(savingsAccountDTO.getInterestRate());
        savingsRepository.save(savings);
        CreateCheckingAccountResponseDTO createCheckingAccountResponseDTO = new CreateCheckingAccountResponseDTO();
        createCheckingAccountResponseDTO.setId(savings.getId());
        createCheckingAccountResponseDTO.setClassName("Savings Account");

        return createCheckingAccountResponseDTO;

    }

    /**
     * This method will create a new Saving Account and it will assign it to a existing Account Holder.
     * @param savingDTO
     * @return
     */
    public Savings createSavingsForExistingAccountHolder(CreateSavingsAccountDTO savingDTO){
        AccountHolder accountHolder = accountHolderRepository.findById(
                savingDTO.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account Holder Not Found")
        );
        Money balance = new Money(new BigDecimal(savingDTO.getBalance()));
        Money minimumBalance = new Money(new BigDecimal(savingDTO.getMinimumBalance()));
        BigDecimal interestRate = savingDTO.getInterestRate();

        return savingsRepository.save(new Savings(balance,accountHolder,minimumBalance,interestRate));

    }

    /**
     * This returns a list of Savings Accounts.
     * @return
     */
    public List<Savings> savingsList(){
        return savingsRepository.findAll();
    }

}
