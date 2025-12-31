package com.nano.banking.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransactionRequest {
    private UUID accountId;
    private Double amount;
}
