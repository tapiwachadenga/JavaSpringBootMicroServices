package com.mr.tApps.paymentservice.repository;

import com.mr.tApps.paymentservice.model.Payment;
// import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentOrderId(Long paymentOrderId);
}
