package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository  extends JpaRepository<UserAddress, Long> {
}
