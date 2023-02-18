package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <Address,Long> {
}
