package com.fezor.spring_finance_control.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    @NotBlank(message = "The email is mandatory")
    private String email;

    @NotBlank(message = "The password is mandatory")
    private String password;

    @NotBlank(message = "The username is mandatory")
    private String username;
}
