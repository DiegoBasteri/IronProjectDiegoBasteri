package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
//    List<Transaction> findByPrimaryOwnerUsername (String username);
  //  List<Transaction> findByAccountSender(BaseCheckingAccount primaryOwner);
}
