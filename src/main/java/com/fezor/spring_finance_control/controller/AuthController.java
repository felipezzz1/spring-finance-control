package com.fezor.spring_finance_control.controller;

import com.fezor.spring_finance_control.dto.LoginRequest;
import com.fezor.spring_finance_control.dto.LoginResponse;
import com.fezor.spring_finance_control.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        String token = service.login(request);

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
