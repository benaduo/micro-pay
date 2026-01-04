package com.nano.payment.consumer;

import com.nano.payment.consumer.services.IPaymentConsumerService;
import com.nano.shared.dtos.PaymentCallbackRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);
    private final IPaymentConsumerService paymentConsumerService;

    public PaymentConsumer(IPaymentConsumerService paymentConsumerService) {
        this.paymentConsumerService = paymentConsumerService;
    }

    @KafkaListener(topics = "${kafka.topics.payment-callbacks}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentCallback(PaymentCallbackRequest message) {
        try {
            log.info("Received message: {}", message);
            System.out.println("Received PaymentCallbackRequest: " + message);
            paymentConsumerService.processPaymentCallback(message);
        } catch (Exception e) {
            log.error("Error processing PaymentCallbackRequest", e);
        }
    }
}
