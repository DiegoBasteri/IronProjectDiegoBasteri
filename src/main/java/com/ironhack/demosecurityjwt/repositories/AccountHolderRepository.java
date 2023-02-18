package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderRepository extends JpaRepository <AccountHolder,Long> {
    AccountHolder findByUsername (String username);
}
