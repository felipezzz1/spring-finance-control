package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.dto.LoginRequest;
import com.fezor.spring_finance_control.model.User;
import com.fezor.spring_finance_control.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public String login(LoginRequest request) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = (User) auth.getPrincipal();

        return jwtService.generateToken(user);
    }
}
