package com.ironhack.demosecurityjwt.dtos;

import lombok.Data;

@Data
public class CreateThirdPartyAccountDTO {
    private String username;
    private String password;
    private String name;
    private String hashKey;
}
