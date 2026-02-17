package com.fezor.spring_finance_control.repository;

import com.fezor.spring_finance_control.dto.UserResponse;
import com.fezor.spring_finance_control.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
