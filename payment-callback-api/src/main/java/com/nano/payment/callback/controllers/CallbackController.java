package com.nano.payment.callback.controllers;

import com.nano.payment.callback.services.ICallbackService;
import com.nano.shared.dtos.ApiResponse;
import com.nano.shared.dtos.PaymentCallbackRequest;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments/callback")
public class CallbackController {
    private final ICallbackService callbackService;

    @PostMapping
    @PermitAll
    public ApiResponse<Void> callback(@RequestBody PaymentCallbackRequest paymentCallbackRequest) {
        return callbackService.processPaymentCallback(paymentCallbackRequest);
    }
}
