package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
