package com.fezor.spring_finance_control.repository;

import com.fezor.spring_finance_control.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByUsername(String username);
}
