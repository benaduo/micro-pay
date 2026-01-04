package com.nano.payment.controllers;

import com.nano.payment.dtos.PaymentRequest;
import com.nano.payment.dtos.PaymentResponse;
import com.nano.payment.services.IPaymentService;
import com.nano.shared.dtos.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    private final IPaymentService paymentService;

    @PostMapping("process")
    public ApiResponse<PaymentResponse> addPayment(@RequestBody PaymentRequest request) {
       return paymentService.processPayment(request);
    }
}
