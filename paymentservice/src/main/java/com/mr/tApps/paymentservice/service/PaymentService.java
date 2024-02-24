package com.mr.tApps.paymentservice.service;

import com.mr.tApps.paymentservice.payload.request.PaymentRequest;
import com.mr.tApps.paymentservice.payload.response.PaymentResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    Long doPayment(PaymentRequest paymentRequest);
    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
