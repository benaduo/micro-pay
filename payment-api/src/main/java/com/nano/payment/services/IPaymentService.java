package com.nano.payment.services;

import com.nano.payment.dtos.PaymentRequest;
import com.nano.payment.dtos.PaymentResponse;
import com.nano.shared.dtos.ApiResponse;

public interface IPaymentService {
    ApiResponse<PaymentResponse> processPayment(PaymentRequest request);
}
