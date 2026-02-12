package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.model.User;
import com.fezor.spring_finance_control.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public boolean auth(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not Found"));

        return encoder.matches(password, user.getPassword());
    }
}
