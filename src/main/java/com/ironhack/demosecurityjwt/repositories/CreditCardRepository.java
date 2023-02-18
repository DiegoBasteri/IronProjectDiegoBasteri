package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository <CreditCard,Long> {
}
