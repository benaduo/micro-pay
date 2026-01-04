package com.nano.payment.consumer.entities;

import com.nano.shared.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity {
    private String accountId;
    private String referenceNumber;
    private String transactionDescription;
    private Double amount;
    private String paymentMethod;
    private String status;
    private String externalTransactionDescription;
}