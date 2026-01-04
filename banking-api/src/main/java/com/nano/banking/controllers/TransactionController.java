package com.nano.banking.controllers;

import com.nano.banking.dtos.CreateTransactionRequest;
import com.nano.banking.dtos.TransactionResponse;
import com.nano.banking.services.ITransactionService;
import com.nano.shared.dtos.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    @PostMapping()
    public ApiResponse<TransactionResponse> createTransaction(CreateTransactionRequest request)
    {
        return transactionService.createTransaction(request);
    }
}
