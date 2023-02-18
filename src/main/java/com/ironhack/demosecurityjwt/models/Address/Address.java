package com.ironhack.demosecurityjwt.models.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.demosecurityjwt.models.BankRoles.AccountHolder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdAddres;


    private String street;
    private String city;
    private String country;
    private Integer zipCode;



     @OneToMany(mappedBy = "address",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     @JsonIgnore
    private List<AccountHolder> accountHolders;


    public Address(String street,String city, String country, Integer zipCode) {
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.street = street;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getIdAddres() {
        return IdAddres;
    }

    public void setIdAddres(Long idAddres) {
        IdAddres = idAddres;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<AccountHolder> accountHolders) {
        this.accountHolders = accountHolders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(IdAddres, address.IdAddres) && Objects.equals(city, address.city) && Objects.equals(country, address.country) && Objects.equals(zipCode, address.zipCode) && Objects.equals(accountHolders, address.accountHolders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdAddres, city, country, zipCode, accountHolders);
    }
}
