package com.nano.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCallbackRequest {
    private UUID paymentId;
    private String status;
    private String transactionReference;
    private String message;
}
