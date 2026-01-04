package com.nano.payment.consumer.repositories;


import com.nano.payment.consumer.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByReferenceNumber(String referenceNumber);
}
