package com.ironhack.demosecurityjwt.models.BankRoles;


import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.Users.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Admin extends User {

    public Admin(Long id, String name, String username, String password, Collection<Role> roles) {
        super(id, name, username, password, roles);
    }
}
