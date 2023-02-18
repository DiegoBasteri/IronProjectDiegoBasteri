package com.ironhack.demosecurityjwt.models.BankRoles;

import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.Users.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty(Long id, String name, String username, String password, Collection<Role> roles, String hashedKey) {
        super(id, name, username, password, roles);
        this.hashedKey = hashedKey;
    }

    public ThirdParty(Long id, String name, String username, String password, Collection<Role> roles) {
        super(id, name, username, password, roles);
    }
}
