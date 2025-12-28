package com.nano.banking.services;

import com.nano.banking.dtos.CreateTransactionRequest;
import com.nano.banking.dtos.TransactionResponse;
import com.nano.shared.dtos.ApiResponse;

public interface ITransactionService {
    ApiResponse<TransactionResponse> createTransaction(CreateTransactionRequest request);
    ApiResponse<TransactionResponse> getTransactionByReferenceNumber(String transactionId);
}
