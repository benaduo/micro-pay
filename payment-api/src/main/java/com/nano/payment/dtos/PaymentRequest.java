package com.nano.payment.dtos;


import lombok.Data;


@Data
public class PaymentRequest {
    private String accountId;
    private String referenceNumber;
    private String transactionDescription;
    private Double amount;
    private String paymentMethod;
}
