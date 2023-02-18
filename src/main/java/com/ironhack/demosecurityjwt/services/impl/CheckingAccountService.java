package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.dtos.CreateCheckingAccountDTO;
import com.ironhack.demosecurityjwt.dtos.CreateCheckingAccountResponseDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import com.ironhack.demosecurityjwt.models.Accounts.StudentCheckingAccount;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CheckingAccountService {
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    TransactionRepository transactionRepository;
    /**
    Method for creating a checking account or a student account in case the AccountHolder is < 18*/
   public CreateCheckingAccountResponseDTO newAccount (CreateCheckingAccountDTO checkingAccountDTO){
        Address address = new Address(
                checkingAccountDTO.getAddressDTO().getStreet(),
                checkingAccountDTO.getAddressDTO().getCity(),
                checkingAccountDTO.getAddressDTO().getCountry(),
                checkingAccountDTO.getAddressDTO().getZipCode()
        );
        addressRepository.save(address);
        AccountHolder accountHolder = new AccountHolder(
               null,
                checkingAccountDTO.getName(),
                checkingAccountDTO.getUsername(),
                passwordEncoder.encode(checkingAccountDTO.getPassword()),
                new ArrayList<>(),
                checkingAccountDTO.getDateOfBirth(),
                address,
                address
        );

        accountHolder = accountHolderRepository.save(accountHolder);
        userService.addRoleToUser(accountHolder.getUsername(),"ROLE_ACCOUNT_HOLDER");

        LocalDate now = LocalDate.now();
        LocalDate birthDate = accountHolder.getDateOfBirth();
        Period age = Period.between(birthDate,now);

        CreateCheckingAccountResponseDTO createCheckingAccountResponseDTO = new CreateCheckingAccountResponseDTO();

        if (age.getYears() >= 24) {
            CheckingAccount checkingAccount = new CheckingAccount();
            checkingAccount.setPrimaryOwner(accountHolder);
            checkingAccount = checkingAccountRepository.save(checkingAccount);
            checkingAccount.setBalance(new Money(new BigDecimal(checkingAccountDTO.getBalance())));
            checkingAccount.setStatus(Status.ACTIVE);
            createCheckingAccountResponseDTO.setId(checkingAccount.getId());
            createCheckingAccountResponseDTO.setClassName("Checking Account");

        } else {
            StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount();
            studentCheckingAccount.setPrimaryOwner(accountHolder);
            studentCheckingAccount.setBalance(new Money(new BigDecimal(checkingAccountDTO.getBalance())));
            studentCheckingAccount.setStatus(Status.ACTIVE);
            studentCheckingAccount = studentCheckingRepository.save(studentCheckingAccount);
            createCheckingAccountResponseDTO.setId(studentCheckingAccount.getId());
            createCheckingAccountResponseDTO.setClassName("Student Account");
        }

        return createCheckingAccountResponseDTO;
    }

    /** Method for list of checkingAccounts*/
    public List <CheckingAccount> getAllCheckingAccounts(){
        return checkingAccountRepository.findAll();
    }
    /** Method for list of StudentAccounts*/
    public List<StudentCheckingAccount> getAllStudentAccounts(){
        return studentCheckingRepository.findAll();
    }
}
