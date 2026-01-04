package com.nano.payment.callback.services;

import com.nano.shared.dtos.ApiResponse;
import com.nano.shared.dtos.PaymentCallbackRequest;

public interface ICallbackService {
    ApiResponse<Void> processPaymentCallback(PaymentCallbackRequest callbackData);
}
