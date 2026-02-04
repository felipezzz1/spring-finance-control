package com.fezor.spring_finance_control.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class TransactionResponse {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String type;
    private String categoryName;
}
