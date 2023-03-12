package com.company.mspayment.mapper;

import com.company.mspayment.entity.Payment;
import com.company.mspayment.model.request.PaymentRequest;
import com.company.mspayment.model.response.PaymentResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentMapper {

    public static Payment mapRequestToEntity(PaymentRequest request){
        return Payment.builder()
                .amount(request.getAmount())
                .description(request.getDescription())
                .build();
    }

    public static PaymentResponse mapEntityToResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .description(payment.getDescription())
                .responseAt(LocalDate.now())
                .build();
    }


}
