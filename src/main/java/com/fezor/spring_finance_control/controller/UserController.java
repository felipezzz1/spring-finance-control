package com.fezor.spring_finance_control.controller;

import com.fezor.spring_finance_control.dto.TransactionResponse;
import com.fezor.spring_finance_control.dto.UserRequest;
import com.fezor.spring_finance_control.dto.UserResponse;
import com.fezor.spring_finance_control.mapper.TransactionMapper;
import com.fezor.spring_finance_control.mapper.UserMapper;
import com.fezor.spring_finance_control.model.Transaction;
import com.fezor.spring_finance_control.model.User;
import com.fezor.spring_finance_control.service.TransactionService;
import com.fezor.spring_finance_control.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final TransactionService transactionService;
    private final UserMapper mapper;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        User user = service.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(mapper.toResponse(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = service.findAll();
        return ResponseEntity.ok(mapper.toResponseList(users));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.findById(id)));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> findByUserId(@PathVariable Long id){
        List<Transaction> transactions = transactionService.findByUserId(id);
        return ResponseEntity.ok(transactionMapper.toResponseList(transactions));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        User updatedUser = service.update(id, request);
        return ResponseEntity.ok(mapper.toResponse(updatedUser));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
