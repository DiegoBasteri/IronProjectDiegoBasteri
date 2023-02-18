package com.ironhack.demosecurityjwt.models.Transaction;

import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;
import com.ironhack.demosecurityjwt.models.Money.Money;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idAccountSender")
    private BaseCheckingAccount accountSender;

    @ManyToOne
    @JoinColumn(name = "idAccountReciever")
    private BaseCheckingAccount accountReciever;

    @NotNull
    private LocalDateTime transferDate = LocalDateTime.now();
    private Money amount;

    public Transaction() {
    }
    public Transaction(BaseCheckingAccount accountSender, BaseCheckingAccount accountReciever, Money amount) {
        this.accountSender = accountSender;
        this.accountReciever = accountReciever;
        this.amount = amount;
    }
    public Integer getIdTransaction() {
        return id;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.id = idTransaction;
    }

    public BaseCheckingAccount getAccountSender() {
        return accountSender;
    }

    public void setAccountSender(BaseCheckingAccount accountSender) {
        this.accountSender = accountSender;
    }

    public BaseCheckingAccount getAccountReciever() {
        return accountReciever;
    }

    public void setAccountReciever(BaseCheckingAccount accountReciever) {
        this.accountReciever = accountReciever;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}
