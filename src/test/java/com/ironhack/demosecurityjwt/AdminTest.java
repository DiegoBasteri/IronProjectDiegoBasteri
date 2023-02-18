package com.ironhack.demosecurityjwt;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import com.ironhack.demosecurityjwt.Enums.AdminModifyBalanceType;
import com.ironhack.demosecurityjwt.dtos.ModifyBalanceDTO;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.repositories.BaseCheckingAccountRepository;
import com.ironhack.demosecurityjwt.services.impl.CheckingAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;
/*
@SpringBootTest

public class AdminTest {

    @Autowired
    private CheckingAccountService checkingAccountService;
    @Autowired
    BaseCheckingAccountRepository baseCheckingAccountRepository;

    @Test
    public void testModifyBalanceOfAccount() {
        // Create a checking account with a balance of 1000
        BaseCheckingAccount account = new BaseCheckingAccount(new Money(new BigDecimal("1000")));
        account = baseCheckingAccountRepository.save(account);

        // Create a ModifyBalanceDTO with an amount of 500
        ModifyBalanceDTO modifyBalanceDTO = new ModifyBalanceDTO(account.getId(), "500");

        // Increase the balance of the account by 500
        AdminModifyBalanceType modifyBalanceType = AdminModifyBalanceType.INCREASE_AMOUNT;
        account = checkingAccountService.modifyBalanceOfAccount(modifyBalanceDTO, modifyBalanceType);

        // Check that the balance is now 1500
        assertEquals(new BigDecimal("1500"), account.getBalance().getAmount());

        // Decrease the balance of the account by 1000
        modifyBalanceType = AdminModifyBalanceType.DECREASE_AMOUNT;
        modifyBalanceDTO = new ModifyBalanceDTO(account.getId(), "1000");
        account = checkingAccountService.modifyBalanceOfAccount(modifyBalanceDTO, modifyBalanceType);

        // Check that the balance is now 500
        assertEquals(new BigDecimal("500"), account.getBalance().getAmount());

        // Attempt to decrease the balance by more than the current balance
        modifyBalanceDTO = new ModifyBalanceDTO(account.getId(), "1000");
        assertThrows(ResponseStatusException.class, () -> checkingAccountService.modifyBalanceOfAccount(modifyBalanceDTO, modifyBalanceType));
    }


}

 */
