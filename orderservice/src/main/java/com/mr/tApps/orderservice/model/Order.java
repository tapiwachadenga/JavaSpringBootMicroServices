package com.mr.tApps.orderservice.model;

import com.mr.tApps.orderservice.util.OrderStatus;
// import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "id")
    private Long productId;

    @Column(name = "quantity")
    private Long orderQuantity;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "total_amount")
    private BigDecimal orderAmount;
}
