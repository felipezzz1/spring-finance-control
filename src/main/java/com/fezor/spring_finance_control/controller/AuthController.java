package com.fezor.spring_finance_control.controller;

import com.fezor.spring_finance_control.dto.LoginRequest;
import com.fezor.spring_finance_control.dto.LoginResponse;
import com.fezor.spring_finance_control.service.AuthService;
import com.fezor.spring_finance_control.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        String token = service.login(request);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
