package com.fezor.spring_finance_control.controller;

import com.fezor.spring_finance_control.dto.TransactionRequest;
import com.fezor.spring_finance_control.dto.TransactionResponse;
import com.fezor.spring_finance_control.mapper.TransactionMapper;
import com.fezor.spring_finance_control.model.Transaction;
import com.fezor.spring_finance_control.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapper mapper;

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest request) {

        Transaction transaction = service.create(request);

        URI location = URI.create("api/v1/transactions" + transaction.getId());

        return  ResponseEntity.created(location)
                .body(mapper.toResponse(transaction));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findAll() {
        List<Transaction> transactions = service.findAll();
        return ResponseEntity.ok(mapper.toResponseList(transactions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.toResponse(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable Long id, @Valid @RequestBody TransactionRequest request){
        Transaction updatedTransaction = service.update(id, request);
        return ResponseEntity.ok(mapper.toResponse(updatedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
