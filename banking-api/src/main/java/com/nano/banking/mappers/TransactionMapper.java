package com.nano.banking.mappers;

import com.nano.banking.dtos.CreateTransactionRequest;
import com.nano.banking.dtos.TransactionResponse;
import com.nano.banking.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
 TransactionResponse toTransactionResponse(Transaction transaction);
 Transaction toTransactionEntity(CreateTransactionRequest request);
}
