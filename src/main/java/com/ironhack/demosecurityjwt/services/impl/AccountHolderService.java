package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.dtos.AccountHolderDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.repositories.AccountHolderRepository;
import com.ironhack.demosecurityjwt.repositories.AddressRepository;
import com.ironhack.demosecurityjwt.repositories.RoleRepository;
import com.ironhack.demosecurityjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    public List<AccountHolder> accountHolders(){
        return accountHolderRepository.findAll();
    }

    public AccountHolder newAccountHolder (AccountHolderDTO accountHolderDTO){
        Address address = new Address(
            accountHolderDTO.getStreet(),
            accountHolderDTO.getCity(),
            accountHolderDTO.getCountry(),
            accountHolderDTO.getZipCode()
        );
        addressRepository.save(address);
        AccountHolder accountHolder = new AccountHolder(
            null,
            accountHolderDTO.getName(),
            accountHolderDTO.getUsername(),
            passwordEncoder.encode(accountHolderDTO.getPassword()),
            new ArrayList<>(),
            accountHolderDTO.getDateofbirth(),
            address,
            null

        );
    accountHolder = accountHolderRepository.save(accountHolder);
    userService.addRoleToUser(accountHolder.getUsername(), "ROLE_ACCOUNT_HOLDER");

    return accountHolder;
    }

    public List<BaseCheckingAccount> findByPrimaryOwnerAccounts(Authentication authentication){
        String username = authentication.getPrincipal().toString();
        return accountHolderRepository.findByUsername(authentication.getPrincipal().toString()).getPrimaryAccounts();
    }
}


