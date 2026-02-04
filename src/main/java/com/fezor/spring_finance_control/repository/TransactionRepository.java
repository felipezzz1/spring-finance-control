package com.fezor.spring_finance_control.repository;

import com.fezor.spring_finance_control.model.Transaction;
import com.fezor.spring_finance_control.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findDateBetween(LocalDate start, LocalDate end);

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByCategoryId(Long categoryId);

    @Query("SELECT SUM(t.amount) FROM transaction t WHERE t.type = :type")
    BigDecimal sumAmountByType(@Param("type") TransactionType type);
}
