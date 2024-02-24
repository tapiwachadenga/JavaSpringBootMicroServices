package com.mr.tApps.orderservice.payload.response;

import com.mr.tApps.orderservice.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mr.tApps.orderservice.util.PaymentMode;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private Long paymentId;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
    private Long amount;
    private Instant paymentDate;
    private Long orderId;
}
