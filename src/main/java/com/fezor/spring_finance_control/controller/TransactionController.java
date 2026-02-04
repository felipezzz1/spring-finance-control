package com.fezor.spring_finance_control.controller;

import com.fezor.spring_finance_control.dto.TransactionRequest;
import com.fezor.spring_finance_control.dto.TransactionResponse;
import com.fezor.spring_finance_control.mapper.TransactionMapper;
import com.fezor.spring_finance_control.model.Transaction;
import com.fezor.spring_finance_control.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = service.create(request);
        return mapper.toResponse(transaction);
    }

    @GetMapping
    public List<TransactionResponse> findAll() {
        List<Transaction> transactions = service.findAll();
        return mapper.toResponseList(transactions);
    }
}
