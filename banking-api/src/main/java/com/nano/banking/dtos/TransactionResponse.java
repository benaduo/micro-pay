package com.nano.banking.dtos;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class TransactionResponse implements Serializable {
    private UUID id;
    private Double amount;
    private String paymentMethod;
    private String description;
    private String transactionType;
    private String accountId;
    private String referenceNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
