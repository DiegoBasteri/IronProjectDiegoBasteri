package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository <CheckingAccount,Long> {
}
