package com.nano.payment.consumer.services;

import com.nano.shared.dtos.PaymentCallbackRequest;

public interface IPaymentConsumerService {
    void processPaymentCallback(PaymentCallbackRequest message);
}
