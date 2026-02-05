package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.dto.TransactionRequest;
import com.fezor.spring_finance_control.model.Category;
import com.fezor.spring_finance_control.model.Transaction;
import com.fezor.spring_finance_control.model.TransactionType;
import com.fezor.spring_finance_control.repository.CategoryRepository;
import com.fezor.spring_finance_control.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Transaction create(TransactionRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public BigDecimal sumAmountByType(TransactionType type) {
        return transactionRepository.findByType(type)
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalBalance() {
        BigDecimal revenues = sumAmountByType(TransactionType.REVENUE);
        BigDecimal expenses = sumAmountByType(TransactionType.EXPENSE);

        revenues = (revenues != null) ? revenues : BigDecimal.ZERO;
        expenses = (expenses != null) ? expenses : BigDecimal.ZERO;

        return revenues.subtract(expenses);
    }
}
