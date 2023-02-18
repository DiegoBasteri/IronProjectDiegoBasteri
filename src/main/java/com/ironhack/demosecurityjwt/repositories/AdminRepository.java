package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.BankRoles.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin,Long> {
}
