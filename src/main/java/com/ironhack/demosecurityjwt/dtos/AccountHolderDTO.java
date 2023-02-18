package com.ironhack.demosecurityjwt.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountHolderDTO {

private String username;
private String password;
    private String name;
    private LocalDate dateofbirth;
    private String street;
    private String city;
    private String country;
    private Integer zipCode;
    private String mailingAddress;



    public AccountHolderDTO(String name, LocalDate dateofbirth, String street, String city, String country, Integer zipCode, String mailingAddress, Long user) {
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.mailingAddress = mailingAddress;

    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }


    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}

