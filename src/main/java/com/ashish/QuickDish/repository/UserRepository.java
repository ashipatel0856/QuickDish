package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
}
