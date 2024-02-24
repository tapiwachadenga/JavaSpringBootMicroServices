package com.mr.tApps.paymentservice.service;

import com.mr.tApps.paymentservice.exception.PaymentServiceCustomException;
import com.mr.tApps.paymentservice.model.Payment;
import com.mr.tApps.paymentservice.payload.ErrorCode;
import com.mr.tApps.paymentservice.payload.request.PaymentRequest;
import com.mr.tApps.paymentservice.payload.response.PaymentResponse;
import com.mr.tApps.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository paymentRepository;

    // @KafkaListener(
    //        topics = "${spring.kafka.topic.name}",
    //       groupId = "${spring.kafka.consumer.group-id}"
    // )
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {

        LOGGER.info("PaymentServiceImpl | doPayment is called");
        LOGGER.info("PaymentServiceImpl | doPayment | Recording Payment Details: {}", paymentRequest);

        Payment payment = Payment.builder()
                .paymentOrderId(paymentRequest.getPaymentOrderId())
                .paymentAmount(paymentRequest.getPaymentAmount())
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode())
                .paymentStatus(paymentRequest.getPaymentStatus())
                .paymentRefNumber(paymentRequest.getPaymentRefNumber())
                .build();
        // return String.format("Payment wit id %d successfully made.", payment.getPaymentId());
        LOGGER.info("PaymentServiceImpl | doPayment | payment created: {}", payment.toString());

        paymentRepository.save(payment);

        return payment.getPaymentId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long paymentOrderId) {

        LOGGER.info("PaymentServiceImpl | getPaymentDetailsByOrderId is called");
        LOGGER.info("PaymentServiceImpl | getPaymentDetailsByOrderId | Getting payment details for the Order Id: {}", paymentOrderId);

        Payment transactionDetails
                = paymentRepository.findByPaymentOrderId(paymentOrderId)
                .orElseThrow(() -> new PaymentServiceCustomException(
                        "TransactionDetails with given id not found",
                        ErrorCode.PAYMENT_NOT_FOUND));

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getPaymentId())
                .paymentMode(transactionDetails.getPaymentMode())
                .paymentDate(transactionDetails.getPaymentDate())
                .paymentOrderId(transactionDetails.getPaymentOrderId())
                .paymentStatus(transactionDetails.getPaymentStatus())
                .paymentAmount(transactionDetails.getPaymentAmount())
                .build();

        LOGGER.info("PaymentServiceImpl | getPaymentDetailsByOrderId | paymentResponse: {}", paymentResponse.toString());

        return paymentResponse;

    }
}
