package com.mr.tApps.paymentservice.payload.response;

import com.mr.tApps.paymentservice.util.PaymentMode;
import com.mr.tApps.paymentservice.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private Long paymentId;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
    private BigDecimal paymentAmount;
    private Instant paymentDate;
    private Long paymentOrderId;
}
