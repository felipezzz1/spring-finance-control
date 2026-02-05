package com.fezor.spring_finance_control.mapper;

import com.fezor.spring_finance_control.dto.TransactionRequest;
import com.fezor.spring_finance_control.dto.TransactionResponse;
import com.fezor.spring_finance_control.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "category.name", target = "categoryName")
    TransactionResponse toResponse(Transaction transaction);

    List<TransactionResponse> toResponseList(List<Transaction> transactions);

    Transaction toEntity(TransactionRequest request);
}
