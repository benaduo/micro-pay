package com.nano.payment.services;

import com.nano.payment.dtos.PaymentRequest;
import com.nano.payment.dtos.PaymentResponse;
import com.nano.payment.entities.Payment;
import com.nano.payment.mappers.PaymentMapper;
import com.nano.payment.repositories.PaymentRepository;
import com.nano.shared.dtos.ApiResponse;
import com.nano.shared.enums.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final WebClient webClient;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, WebClient webClient) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.webClient = webClient;
    }

    @Override
    public ApiResponse<PaymentResponse> processPayment(PaymentRequest request) {

        try {
            log.info("Processing Payment Request: {}", request);

            Payment payment = paymentMapper.toPaymentEntity(request);
            payment.setStatus(TransactionStatus.PROCESSING.toString());
            paymentRepository.save(payment);


            /// Make call to external service to process payment
            var paymentRequest = new Object() {
                public String paymentId = payment.getId().toString();
                public String transactionReference = request.getReferenceNumber();
                public Double amount = request.getAmount();
            };
            var response =
                    webClient
                            .post()
                            .bodyValue(paymentRequest)
                            .retrieve()
                            .bodyToMono(ApiResponse.class)
                            .block();
            log.info("Received response from external payment service: {}", response);
            /// End of external service call

            PaymentResponse paymentResponse = paymentMapper.toPaymentResponse(payment);
            log.info("Payment is being processed: {}", paymentResponse);

            return new ApiResponse<>(true, "Payment is being processed", paymentResponse, null);
        } catch (Exception e) {
            log.error("Error processing payment: {}", e.getMessage());
            return new ApiResponse<>(false, "Failed to process payment", null, "PAYMENT_PROCESSING_ERROR");
        }

    }
}
