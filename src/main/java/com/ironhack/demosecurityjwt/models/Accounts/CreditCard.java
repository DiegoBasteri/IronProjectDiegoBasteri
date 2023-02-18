package com.ironhack.demosecurityjwt.models.Accounts;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
@NoArgsConstructor
@Entity
@Getter
@Setter
public class CreditCard extends BaseCheckingAccount{


    @DecimalMin(value = "100")
    @DecimalMax(value = "100000")
    private BigDecimal creditLimit = new BigDecimal("100");

    @DecimalMin(value = "0.1")
    @DecimalMax(value = "0.2")
    private BigDecimal interestRate = new BigDecimal("0.2");

    private LocalDate lastInterestDate;

    public CreditCard(BigDecimal creditLimit, BigDecimal interestRate, LocalDate lastInterestDate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.lastInterestDate = lastInterestDate;
    }

    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Status status, BigDecimal creditLimit, BigDecimal interestRate, LocalDate lastInterestDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, creationDate, status);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.lastInterestDate = lastInterestDate;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    /*private void addInterestIfNecessary() {
        LocalDate currentDate = LocalDate.now();
        Period elapsed = Period.between(lastInterestDate, currentDate);
        if (elapsed.getMonths() > 0) {
            BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal interest = balance.getAmount().multiply(monthlyInterestRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            balance.increaseAmount(interest);
            lastInterestDate = currentDate;
        }
    }

     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(creditLimit, that.creditLimit) && Objects.equals(interestRate, that.interestRate) && Objects.equals(lastInterestDate, that.lastInterestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditLimit, interestRate, lastInterestDate);
    }
}



