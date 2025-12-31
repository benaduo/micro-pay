package com.nano.banking.repositories;

import com.nano.banking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByAccountId(UUID accountId);
}
