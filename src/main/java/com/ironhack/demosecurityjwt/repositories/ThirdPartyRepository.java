package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.BankRoles.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Long> {
    ThirdParty findByHashedKey (String hasheKey);
}
