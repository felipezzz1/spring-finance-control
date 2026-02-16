package com.fezor.spring_finance_control.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    @NotBlank(message = "The user email is mandatory")
    private String email;

    @NotBlank(message = "The password is mandatory")
    private String password;
}
