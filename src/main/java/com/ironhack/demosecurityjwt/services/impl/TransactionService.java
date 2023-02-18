package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.dtos.TransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionResponseDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Accounts.CheckingAccount;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    BaseCheckingAccountRepository baseCheckingAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    /**
     * Transaction Method that uses transactionDTO to recieve the accounts Id's and the amount of the transaction
     * @param transactionDTO
     * @return
     */
    public TransactionResponseDTO createTransaction(TransactionDTO transactionDTO) {
        BaseCheckingAccount accountSender = checkingAccountRepository.findById(transactionDTO.getIdAccountSender()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Sender Doesn´t Exist"));
        BaseCheckingAccount accountReciever = checkingAccountRepository.findById(transactionDTO.getIdAccountReciever()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Reciever Doesn´t Exist"));

        if (accountSender.getBalance().compareTo(BigDecimal.valueOf(transactionDTO.getAmount())) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient founds");
        }
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setOldAmount(accountSender.getBalance().getAmount().doubleValue());

        Transaction transaction = new Transaction();

        transaction.setAccountSender(accountSender);
        transaction.setAccountReciever(accountReciever);
        transaction.setAmount(new Money(BigDecimal.valueOf(transactionDTO.getAmount())));
        transaction.setTransferDate(LocalDateTime.now());

        accountSender.setBalance(new Money(accountSender.getBalance().decreaseAmount(BigDecimal.valueOf(transactionDTO.getAmount()))));
        accountReciever.setBalance(new Money(accountReciever.getBalance().increaseAmount(BigDecimal.valueOf(transactionDTO.getAmount()))));

        transactionRepository.save(transaction);

        baseCheckingAccountRepository.save(accountReciever);
        baseCheckingAccountRepository.save(accountSender);

        transactionResponseDTO.setUsername(accountSender.getPrimaryOwner().getUsername());
        transactionResponseDTO.setNewAmount(accountSender.getBalance().getAmount().doubleValue());

        return transactionResponseDTO;

    }

    /*public BigDecimal getHighestDailyTotalTransactions(TransactionDTO transactionDTO) {
        List<Transaction> transactions = transactionRepository.findByAccountSender(
                this.checkingAccountRepository.findById());
        Map<LocalDate, BigDecimal> dailyTotals = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getTransferDate().toLocalDate(),
                        Collectors.reducing(BigDecimal.ZERO, transaction -> transaction.getAmount().getAmount(), BigDecimal::add)));
        return dailyTotals.values().stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

     */

}
