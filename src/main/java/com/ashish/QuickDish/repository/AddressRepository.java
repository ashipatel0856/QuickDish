package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface AddressRepository extends JpaRepository<Address, Long> {
}
