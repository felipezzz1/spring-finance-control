package com.fezor.spring_finance_control.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String username;
}
