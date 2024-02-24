package com.mr.tApps.orderservice.payload.response;

import com.mr.tApps.orderservice.util.OrderStatus;
import com.mr.tApps.orderservice.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.mr.tApps.orderservice.util.PaymentMode;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private Instant orderDate;
    private OrderStatus orderStatus;
    private BigDecimal amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private String productName;
        private Long productId;
        private Long productQuantity;
        private Long productPrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentDetails{
        private Long paymentId;
        private PaymentMode paymentMode;
        private PaymentStatus paymentStatus;
        private Instant paymentDate;
    }
}
