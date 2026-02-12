package com.fezor.spring_finance_control.service;

import com.fezor.spring_finance_control.dto.UserRequest;
import com.fezor.spring_finance_control.mapper.UserMapper;
import com.fezor.spring_finance_control.model.User;
import com.fezor.spring_finance_control.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    private void validateTransaction(UserRequest request) {
        if(request.getPassword().length() > 32 || request.getPassword().length() < 8){
            throw new RuntimeException("Password must be between 8 and 32 characters");
        }
        // implement in the future some special characters validation
    }

    @Transactional
    public User create(UserRequest request) {
        validateTransaction(request);

        User user = mapper.toEntity(request);

        return repository.save(user);
    }

    public List<User> findAll() { return repository.findAll(); }

    public User findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Found"));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not Found"));
    }

    public User findByUsername(String username) {
        return (User) repository.findByUsername(username);
    }

    public User update(Long id, UserRequest request) {

        User user = findById(id);

        user.setEmail(request.getEmail());
        user.setUsername(request.getEmail());
        user.setPassword(request.getPassword());

        return repository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        repository.delete(user);
    }
}
