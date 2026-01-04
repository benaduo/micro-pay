package com.nano.payment.mappers;

import com.nano.payment.dtos.PaymentRequest;
import com.nano.payment.dtos.PaymentResponse;
import com.nano.payment.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentResponse toPaymentResponse(Payment payment);
    Payment toPaymentEntity(PaymentRequest paymentRequest);
}
