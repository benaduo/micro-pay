package com.nano.payment.dtos;

import lombok.Data;

@Data
public class PaymentResponse {
    private String referenceNumber;
    private String transactionDescription;
    private Double amount;
    private String paymentMethod;
    private String status;
    private String externalTransactionDescription;
}
