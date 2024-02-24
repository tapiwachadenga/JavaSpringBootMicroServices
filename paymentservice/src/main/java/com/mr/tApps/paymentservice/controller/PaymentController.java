package com.mr.tApps.paymentservice.controller;

import com.mr.tApps.paymentservice.payload.request.PaymentRequest;
import com.mr.tApps.paymentservice.payload.response.PaymentResponse;
import com.mr.tApps.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
// @RequiredArgsConstructor
@AllArgsConstructor
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {

        LOGGER.info("PaymentController | doPayment is called");

        LOGGER.info("PaymentController | doPayment | paymentRequest : " + paymentRequest.toString());

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable Long orderId) {

        LOGGER.info("PaymentController | doPayment is called");

        LOGGER.info("PaymentController | doPayment | orderId : " + orderId);

        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
    }
}
