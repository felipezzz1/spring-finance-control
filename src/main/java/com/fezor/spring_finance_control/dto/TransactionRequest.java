package com.fezor.spring_finance_control.dto;

import com.fezor.spring_finance_control.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class TransactionRequest {
    @NotBlank(message = "The description is mandatory")
    private String description;

    @NotNull(message = "The amount is mandatory")
    @Positive(message = "The value must be higher than zero")
    private BigDecimal amount;

    @NotNull(message = "The date is mandatory")
    private LocalDate date;

    @NotNull(message = "The type is mandatory")
    private TransactionType type;

    @NotNull(message = "The category ID is mandatory")
    private Long categoryId;
}
