package com.nano.payment.consumer.services;

import com.nano.shared.dtos.PaymentCallbackRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumerService {

    @KafkaListener(topics = "${kafka.topics.payment-callbacks}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentCallback(PaymentCallbackRequest message) {
        // Process the payment callback message
        System.out.println("Received payment callback message: " + message);
        // Add your business logic here
    }
}
