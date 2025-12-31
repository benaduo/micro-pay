package com.nano.banking.entities;

import com.nano.shared.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("email")
    private String email;
    @Column("phone_number")
    private String phoneNumber;
    @Column("address")
    private String address;
    @Column("account_id")
    private UUID accountId;
    @Column("fineract_account_id")
    private Long fineractAccountId;
    @Column("fineract_client_id")
    private Long fineractClientId;
}
