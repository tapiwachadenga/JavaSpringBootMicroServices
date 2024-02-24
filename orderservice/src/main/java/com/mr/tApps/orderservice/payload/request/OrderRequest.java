package com.mr.tApps.orderservice.payload.request;

import com.mr.tApps.orderservice.util.OrderStatus;
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
public class OrderRequest {

    private Long productId;
    private BigDecimal productTotalAmount;
    private Long productQuantity;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private String paymentRefNumber;
    private OrderStatus orderStatus;
}
