package com.nano.payment.consumer.entities;

import com.nano.shared.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {
    private Double amount;
    private String paymentMethod;
    private String description;
    private String transactionType;
    private UUID accountId;
    private String referenceNumber;
    private String status;
}
