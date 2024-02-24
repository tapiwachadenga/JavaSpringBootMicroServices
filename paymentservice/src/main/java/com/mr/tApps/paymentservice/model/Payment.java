package com.mr.tApps.paymentservice.model;

import com.mr.tApps.paymentservice.util.PaymentMode;
import com.mr.tApps.paymentservice.util.PaymentStatus;
// import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transaction_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long paymentId;

    @Column(name = "order_id")
    private Long paymentOrderId;

    @Column(name = "mode")
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Column(name = "reference_number")
    private String paymentRefNumber;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "amount")
    private BigDecimal paymentAmount;
}
