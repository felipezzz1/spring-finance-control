package com.fezor.spring_finance_control.repository;

import com.fezor.spring_finance_control.model.Transaction;
import com.fezor.spring_finance_control.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetween(LocalDate start, LocalDate end);

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByCategoryId(Long categoryId);
}
