package com.ironhack.demosecurityjwt.models.BankRoles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.ironhack.demosecurityjwt.models.Address.Address;
import com.ironhack.demosecurityjwt.models.Accounts.BaseCheckingAccount;

import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.Users.User;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

// Class AccountHolder refers to a class where we have all personal data from the user

@Entity
@Getter
@Setter
public class AccountHolder extends User {
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "RidAddres")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "RidMailingAddres")
    private Address mailingAddres;

    @OneToMany(mappedBy = "primaryOwner", fetch = FetchType.EAGER)
    @JsonIgnore
    List<BaseCheckingAccount> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner",fetch = FetchType.EAGER)
    @JsonIgnore
    List<BaseCheckingAccount> secondaryAccounts;

    public AccountHolder(Long id, String name, String username, String password, Collection<Role> roles, LocalDate dateOfBirth, Address address, Address mailingAddres) {
        super(id, name, username, password, roles);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mailingAddres = mailingAddres;
    }

    public AccountHolder() {
    }
}