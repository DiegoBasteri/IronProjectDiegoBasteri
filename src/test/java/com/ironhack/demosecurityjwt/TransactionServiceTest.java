package com.ironhack.demosecurityjwt;

import org.junit.jupiter.api.Test;
import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.dtos.TransactionDTO;
import com.ironhack.demosecurityjwt.dtos.TransactionResponseDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import com.ironhack.demosecurityjwt.repositories.BaseCheckingAccountRepository;
import com.ironhack.demosecurityjwt.repositories.CheckingAccountRepository;
import com.ironhack.demosecurityjwt.repositories.TransactionRepository;
import com.ironhack.demosecurityjwt.services.impl.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @MockBean
    private CheckingAccountRepository checkingAccountRepository;

    @MockBean
    private BaseCheckingAccountRepository baseCheckingAccountRepository;

    @MockBean
    private TransactionRepository transactionRepository;
/*
    @Test
    void testCreateTransaction() {
        // Create mock data
        long senderId = 1L;
        long receiverId = 2L;
        double amount = 100.0;

        BaseCheckingAccount sender = new BaseCheckingAccount();
        sender.setId(senderId);
        sender.setBalance(new Money(new BigDecimal(200)));
        sender.setSecretKey("secret");
        sender.setStatus(Status.ACTIVE);

        BaseCheckingAccount receiver = new BaseCheckingAccount();
        receiver.setId(receiverId);
        receiver.setBalance(new Money(new BigDecimal(0)));
        receiver.setSecretKey("secret");
        receiver.setStatus(Status.ACTIVE);

        TransactionDTO transactionDTO = new TransactionDTO();

        Transaction transaction = new Transaction();
        transaction.setAccountSender(sender);
        transaction.setAccountReciever(receiver);
        transaction.setAmount(new Money(new BigDecimal(amount)));
        transaction.setTransferDate(LocalDateTime.now());

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setOldAmount(sender.getBalance().getAmount().doubleValue());
        transactionResponseDTO.setNewAmount(sender.getBalance().getAmount().subtract(new BigDecimal(amount)).doubleValue());
        //transactionResponseDTO.setUsername(sender.getPrimaryOwner().getUsername());

        // Mock repository methods
        //when(checkingAccountRepository.findById(senderId)).thenReturn(sender);
        //when(checkingAccountRepository.findById(receiverId)).thenReturn(receiver);
        when(baseCheckingAccountRepository.save(sender)).thenReturn(sender);
        when(baseCheckingAccountRepository.save(receiver)).thenReturn(receiver);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Call method and verify response
        TransactionResponseDTO response = transactionService.createTransaction(transactionDTO);
        assertEquals(transactionResponseDTO, response);
    }

 */
}
