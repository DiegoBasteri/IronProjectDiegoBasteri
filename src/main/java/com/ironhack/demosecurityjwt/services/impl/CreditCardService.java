package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.dtos.CreateCheckingAccountResponseDTO;
import com.ironhack.demosecurityjwt.dtos.CreateCreditCardDTO;
import com.ironhack.demosecurityjwt.dtos.CreateSavingsAccountDTO;
import com.ironhack.demosecurityjwt.models.Accounts.CreditCard;
import com.ironhack.demosecurityjwt.models.Accounts.Savings;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.repositories.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CreditCardService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    /**
     * This method will create a new CreditCard from 0 and also create a new AccountHolder for this credit card.
     * @param creditCardDTO
     * @return
     */
    public CreateCheckingAccountResponseDTO newCreditCard(CreateCreditCardDTO creditCardDTO) {
        Address address = new Address(
                creditCardDTO.getAddressDTO().getStreet(),
                creditCardDTO.getAddressDTO().getCity(),
                creditCardDTO.getAddressDTO().getCountry(),
                creditCardDTO.getAddressDTO().getZipCode()
        );
        addressRepository.save(address);
        AccountHolder accountHolder = new AccountHolder(
                null,
                creditCardDTO.getName(),
                creditCardDTO.getUsername(),
                passwordEncoder.encode(creditCardDTO.getPassword()),
                new ArrayList<>(),
                creditCardDTO.getDateOfBirth(),
                address,
                address
        );

        accountHolder = accountHolderRepository.save(accountHolder);

        CreditCard creditCard = new CreditCard();
        creditCard.setPrimaryOwner(accountHolder);
        creditCard.setBalance(new Money(new BigDecimal(creditCardDTO.getBalance())));
        creditCard.setCreditLimit(new BigDecimal(creditCardDTO.getCreditLimit()));
        creditCard.setInterestRate(new BigDecimal(creditCardDTO.getInterestRate()));
        creditCardRepository.save(creditCard);
        CreateCheckingAccountResponseDTO createCheckingAccountResponseDTO = new CreateCheckingAccountResponseDTO();
        createCheckingAccountResponseDTO.setId(creditCard.getId());
        createCheckingAccountResponseDTO.setClassName("Credit Card");

        return createCheckingAccountResponseDTO;
    }

    /**
     * This method is for creating a new credit card and assign it to a existing account holder
     * @param creditCardDTO
     * @return
     */
    public CreditCard createCreditCardOnly(CreateCreditCardDTO creditCardDTO){
        Money balance = new Money(new BigDecimal(creditCardDTO.getBalance()));
        AccountHolder accountHolder = accountHolderRepository.findById(creditCardDTO.getAccountID()).get();
        BigDecimal interestRate = new BigDecimal(creditCardDTO.getInterestRate());
        BigDecimal creditLimit = new BigDecimal(creditCardDTO.getCreditLimit());

        CreditCard creditCard = creditCardRepository.save(new CreditCard(balance,accountHolder,creditLimit,interestRate));

        return creditCard;
    }


}
