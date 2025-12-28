package com.nano.banking.services;

import com.nano.banking.dtos.CreateTransactionRequest;
import com.nano.banking.dtos.TransactionResponse;
import com.nano.shared.dtos.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class TransactionService implements ITransactionService{
    @Override
    public ApiResponse<TransactionResponse> createTransaction(CreateTransactionRequest request) {
        log.debug("Received request to create transaction: {}", request);
        // get account details from account service
        return null;
    }

    @Override
    public ApiResponse<TransactionResponse> getTransactionByReferenceNumber(String transactionId) {
        return null;
    }
}
