package com.mr.tApps.orderservice.payload.request;

import com.mr.tApps.orderservice.util.PaymentMode;
import com.mr.tApps.orderservice.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private Long paymentOrderId;
    private BigDecimal paymentAmount;
    private String paymentRefNumber;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
}
