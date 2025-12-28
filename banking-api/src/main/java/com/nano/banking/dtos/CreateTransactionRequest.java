package com.nano.banking.dtos;

import lombok.Data;

@Data
public class CreateTransactionRequest {
    private String accountId;
    private Double amount;
}
