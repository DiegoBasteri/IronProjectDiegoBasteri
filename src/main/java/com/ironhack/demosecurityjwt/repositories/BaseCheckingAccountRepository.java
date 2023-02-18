package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface BaseCheckingAccountRepository extends JpaRepository <BaseCheckingAccount,Long> {

   //List<BaseCheckingAccount> findByPrimaryOwnerUsername(String username);
}
