package com.ironhack.demosecurityjwt.models.Accounts;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity

public class StudentCheckingAccount extends BaseCheckingAccount {
    public StudentCheckingAccount() {
    }

    public StudentCheckingAccount(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Status status) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate, status);
    }
}
