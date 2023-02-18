package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository <Savings,Long> {
}
