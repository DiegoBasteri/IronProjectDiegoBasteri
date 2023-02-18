package com.ironhack.demosecurityjwt.models.Accounts;

import com.ironhack.demosecurityjwt.Enums.Status;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import com.ironhack.demosecurityjwt.models.Money.Money;
import com.ironhack.demosecurityjwt.models.Transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Base Checking Account with properties like balance, secret key, primary owner, secondary owner,
 * penalty fee, creation date and status.
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseCheckingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Money balance;

    private String secretKey;

    @ManyToOne
    @JoinColumn(name = "id_primary_owner")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "id_secondary_owner")
    private AccountHolder secondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="currency_penalty")),
            @AttributeOverride(name="amount",column=@Column(name="amount_penalty"))
    })
    private Money penaltyFee = new Money(new BigDecimal("40.0"));
    private LocalDate creationDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private BigDecimal monthlyMaintenanceFee = new BigDecimal("12");

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimun_balance")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance"))
    })
    private Money minimumBalance = new Money(new BigDecimal("250"));

    @OneToMany(mappedBy = "id")
    List<Transaction> transactions;


    public BaseCheckingAccount() {
    }

    public BaseCheckingAccount(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Status status) {
        this.balance.increaseAmount(getMinimumBalance());
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
        this.status = status;
    }

    public BaseCheckingAccount(Money balance, String secretKey, AccountHolder primaryOwner) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
    }

    public BaseCheckingAccount(Money balance, AccountHolder primaryOwner, Money minimumBalance) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.minimumBalance = minimumBalance;
    }

    public BaseCheckingAccount(Money balance, AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    //(balance,accountHolder,minimunBalance,interestRate)

    public void deductPenaltyFee(BigDecimal minimumBalance) {
        if (this.balance.compareTo(minimumBalance) < 0) {
            this.balance.decreaseAmount(penaltyFee);
        }
    }

}
