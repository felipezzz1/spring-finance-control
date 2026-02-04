package com.fezor.spring_finance_control.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryRequest {

    @NotBlank(message = "The category name is mandatory")
    private String name;
}
