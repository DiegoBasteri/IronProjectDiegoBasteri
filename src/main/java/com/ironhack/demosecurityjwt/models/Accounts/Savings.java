package com.ironhack.demosecurityjwt.models.Accounts;

import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


/**

 This class represents a Savings account.

 It extends the {@link BaseCheckingAccount} class and has an additional attribute interestRate.

 */

@Entity
@Setter
@Getter
public class Savings extends BaseCheckingAccount{
    private BigDecimal interestRate;
    private Money minimumBalance ;

    private static final BigDecimal MAXIMUM_INTEREST_RATE = new BigDecimal("0.5");
    private static final BigDecimal MINIMUM_INTEREST_RATE = new BigDecimal("0.0025");
    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(new BigDecimal("1000"));

    public Savings() {
    }


    public Savings(BigDecimal interestRate) {
        if (interestRate == null) {
            this.interestRate = MINIMUM_INTEREST_RATE;
        } else if (interestRate.compareTo(MAXIMUM_INTEREST_RATE) == 1 || MINIMUM_INTEREST_RATE.compareTo(interestRate) == 1) {
            throw new IllegalArgumentException("The range is off");
        } else {
            this.interestRate = interestRate;
        }
    }

    public Savings(BigDecimal interestRate, Money minimumBalance) {
        if (interestRate == null) {
            this.interestRate = MINIMUM_INTEREST_RATE;
        } else if (interestRate.compareTo(MAXIMUM_INTEREST_RATE) > 0) {
            throw new IllegalArgumentException("Interest rate is too high");
        } else {
            this.interestRate = interestRate;
        }

        if (minimumBalance == null) {
            this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
        } else if (minimumBalance.getAmount().compareTo(new BigDecimal("100")) < 0) {
            throw new IllegalArgumentException("Minimum balance is too low");
        } else {
            this.minimumBalance = minimumBalance;
        }
    }

    public Savings(Money balance, AccountHolder primaryOwner, Money minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, minimumBalance);
        this.interestRate = interestRate;

    }



    /* public void applyInterest() {
                    LocalDate now = LocalDate.now();
                    LocalDate lastInterestApplied = getLastInterestApplied();
                    if (lastInterestApplied == null || lastInterestApplied.plusYears(1).isBefore(now)) {
                        BigDecimal interest = getBalance().getAmount().multiply(interestRate);
                        Money interestMoney = new Money(interest);
                        creditBalance(interestMoney);
                        setLastInterestApplied(now);
                    }
                }



                @Override
                public Money getBalance() {
                    applyInterest();
                    return super.getBalance();
                }
            */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Savings savings = (Savings) o;
        return Objects.equals(interestRate, savings.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interestRate);
    }
}
