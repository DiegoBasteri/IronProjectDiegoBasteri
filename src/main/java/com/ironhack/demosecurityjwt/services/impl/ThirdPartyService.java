package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.dtos.ThirdPartyTransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionResponseDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.BankRoles.ThirdParty;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ThirdPartyService {
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BaseCheckingAccountRepository baseCheckingAccountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * Transaction for a ThirdParty, this method will find in the thirdParty database if the HashedKey provided it's ok,
     * and then it will realize the transaction.
     * @param thirdPartyTransactionDTO
     * @return
     */
    public TransactionResponseDTO createTransaction(ThirdPartyTransactionDTO thirdPartyTransactionDTO) {
        BaseCheckingAccount accountSender = checkingAccountRepository.findById(
                thirdPartyTransactionDTO.getIdAccountSender()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Sender Doesn´t Exist")
        );
        BaseCheckingAccount accountReciever = checkingAccountRepository.findById(
                thirdPartyTransactionDTO.getIdAccountReciever()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Reciever Doesn´t Exist")
        );
        ThirdParty thirdParty = thirdPartyRepository.findByHashedKey(thirdPartyTransactionDTO.getHashedKey());
        if (thirdParty == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Third Party Not Found");

        if (accountSender.getBalance().compareTo(BigDecimal.valueOf(thirdPartyTransactionDTO.getAmount())) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient founds");
        }

        Transaction transaction = new Transaction();

        transaction.setAccountSender(accountSender);
        transaction.setAccountReciever(accountReciever);
        transaction.setAmount(new Money(BigDecimal.valueOf(thirdPartyTransactionDTO.getAmount())));
        transaction.setTransferDate(LocalDateTime.now());

        accountSender.setBalance(new Money(accountSender.getBalance().decreaseAmount(BigDecimal.valueOf(thirdPartyTransactionDTO.getAmount()))));
        accountReciever.setBalance(new Money(accountReciever.getBalance().increaseAmount(BigDecimal.valueOf(thirdPartyTransactionDTO.getAmount()))));

        transactionRepository.save(transaction);
        baseCheckingAccountRepository.save(accountReciever);
        baseCheckingAccountRepository.save(accountSender);

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setOldAmount(accountSender.getBalance().getAmount().doubleValue());
        transactionResponseDTO.setUsername(accountSender.getPrimaryOwner().getUsername());
        transactionResponseDTO.setNewAmount(accountSender.getBalance().getAmount().doubleValue());
        return transactionResponseDTO;

    }
}
