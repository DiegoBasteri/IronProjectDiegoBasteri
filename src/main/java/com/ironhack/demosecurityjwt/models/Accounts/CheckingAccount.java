package com.ironhack.demosecurityjwt.models.Accounts;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CheckingAccount extends BaseCheckingAccount {
    private BigDecimal monthlyMaintenanceFee = new BigDecimal("12");
    private Money minimumBalance = new Money(new BigDecimal("250"));


    public CheckingAccount() {
    }

    public CheckingAccount(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public CheckingAccount(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Status status) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate, status);

    }

    public CheckingAccount(Money balance, String secretKey, AccountHolder primaryOwner) {
        super(balance, secretKey, primaryOwner);
    }

    public CheckingAccount(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckingAccount that = (CheckingAccount) o;
        return Objects.equals(monthlyMaintenanceFee, that.monthlyMaintenanceFee) && Objects.equals(minimumBalance, that.minimumBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthlyMaintenanceFee, minimumBalance);
    }
}


