package com.nano.payment.callback.services;

import com.nano.shared.dtos.ApiResponse;
import com.nano.shared.dtos.PaymentCallbackRequest;
import com.nano.shared.enums.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class CallbackService implements ICallbackService {
    private final KafkaProducerService kafkaProducerService;
    private final Random random = new Random();

    private static final List<String> FAILURE_MESSAGES = Arrays.asList(
            "Insufficient funds",
            "Card declined",
            "Invalid card details",
            "Transaction timeout",
            "Gateway error"
    );

    public CallbackService(KafkaProducerService kafkaProducerService1) {
        this.kafkaProducerService = kafkaProducerService1;

    }

    @Override
    public ApiResponse<Void> processPaymentCallback(PaymentCallbackRequest callbackData) {
        log.info("Processing payment callback: {}", callbackData);
        simulatePaymentGatewayCallback(callbackData.getPaymentId(), callbackData.getTransactionReference());
        return new ApiResponse<>(true, "Payment callback processing initiated", null, null);
    }

    @Async
    protected void simulatePaymentGatewayCallback(UUID paymentId, String gatewayReference) {
        try {
            // Simulate gateway processing delay (2-5 seconds)
            int delay = 2000 + random.nextInt(3000);
            Thread.sleep(delay);

            // Generate random callback response
            PaymentCallbackRequest callback = generateRandomCallback(paymentId, gatewayReference);

            log.info("Simulated callback for payment {}: Status={}, Message={}",
                    paymentId, callback.getStatus(), callback.getMessage());

            // Publish event to Kafka
            kafkaProducerService.publishEvent(callback);
            log.info("Payment callback event published to Kafka for payment: {}", paymentId);

        } catch (InterruptedException e) {
            log.error("Callback simulation interrupted for payment: {}", paymentId, e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("Error processing callback for payment: {}", paymentId, e);
        }
    }

    private PaymentCallbackRequest generateRandomCallback(UUID paymentId, String gatewayReference) {
        // 70% success, 20% failed, 5% pending, 5% timeout
        double rand = random.nextDouble();
        String status;
        String message;

        if (rand < 0.70) {
            status = TransactionStatus.COMPLETED.name();
            message = "Payment processed successfully";
        } else if (rand < 0.90) {
            status = TransactionStatus.FAILED.name();
            message = FAILURE_MESSAGES.get(random.nextInt(FAILURE_MESSAGES.size()));
        } else if (rand < 0.95) {
            status = TransactionStatus.PENDING.name();
            message = "Payment verification in progress";
        } else {
            status = TransactionStatus.TIMEOUT.name();
            message = "Payment gateway timeout - retry required";
        }


        return new PaymentCallbackRequest(paymentId, status, gatewayReference, message);
    }
}
