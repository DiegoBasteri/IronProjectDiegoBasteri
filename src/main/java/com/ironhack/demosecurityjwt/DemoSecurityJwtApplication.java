package com.ironhack.demosecurityjwt;

import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.BankRoles.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.Users.User;
import com.ironhack.demosecurityjwt.repositories.*;
import com.ironhack.demosecurityjwt.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class DemoSecurityJwtApplication {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, AddressRepository addressRepository,
                          AccountHolderRepository accountHolderRepository,
                          CheckingAccountRepository checkingAccountRepository,
                          CreditCardRepository creditCardRepository,
                          RoleRepository roleRepository,
                          BaseCheckingAccountRepository baseCheckingAccountRepository) {
        return args -> {

            Address address = addressRepository.save(new Address("calle123", "Rosario", "Argentina", 2000));
            addressRepository.save(new Address("calle300", "Rosario", "Argentina", 2000));

            userService.saveRole(new Role(null, "ROLE_ACCOUNT_HOLDER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_THIRD_PARTY"));

            userService.saveUser(new AccountHolder(null,
                    "Diego",
                    "db",
                    "1234",
                    new ArrayList<>(),
                    LocalDate.of(1990, 11, 26),
                    address,
                    null)
            );

            userService.saveUser(new AccountHolder(null,
                    "James Smith",
                    "james",
                    "1234",
                    new ArrayList<>(),
                    LocalDate.of(1980, 8, 22),
                    address,
                    address));

            userService.saveUser(new ThirdParty(null, "Pere A", "pe", "1234", new ArrayList<>(), ("asdd")));
            userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>()));


            userService.addRoleToUser("db", "ROLE_ACCOUNT_HOLDER");
            userService.addRoleToUser("james", "ROLE_ADMIN");
            userService.addRoleToUser("pe", "ROLE_THIRD_PARTY");
            userService.addRoleToUser("chris", "ROLE_ADMIN");

        };

    }
}

