package com.nano.payment.consumer.services;

import com.nano.payment.consumer.repositories.PaymentRepository;
import com.nano.payment.consumer.repositories.TransactionRepository;
import com.nano.shared.dtos.PaymentCallbackRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentConsumerService implements IPaymentConsumerService {

    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;

    public PaymentConsumerService(PaymentRepository paymentRepository, TransactionRepository transactionRepository) {
        this.paymentRepository = paymentRepository;
        this.transactionRepository = transactionRepository;
    }

        public void processPaymentCallback(PaymentCallbackRequest message) {
        try {
            // Process the payment callback message
            log.info("Received Payment Callback Request: {}", message);
            // Add your business logic here

            var payment = paymentRepository.findByReferenceNumber(message.getTransactionReference());
            if (payment == null) {
                log.info("Payment not found for transaction reference: {}", message.getTransactionReference());
                return;
            }
            payment.setStatus(message.getStatus());
            payment.setExternalTransactionDescription(message.getMessage());
            paymentRepository.save(payment);

            var transaction = transactionRepository.findByReferenceNumber(message.getTransactionReference());
            if (transaction == null) {
                System.out.println("Transaction with reference number " + message.getTransactionReference() + " not found.");
                return;
            }
            transaction.setStatus(message.getStatus());
            transactionRepository.save(transaction);

            log.info("Processed Payment Callback Request for payment ID: {}", payment.getId());
        }catch (Exception e){
            log.error("Error processing payment callback: {}", e.getMessage());
        }

    }
}
