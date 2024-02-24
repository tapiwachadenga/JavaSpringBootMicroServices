package com.mr.tApps.paymentservice.exception;

import com.mr.tApps.paymentservice.payload.ErrorCode;

public class PaymentServiceCustomException extends RuntimeException{

    private final ErrorCode errorCode;

    public PaymentServiceCustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}


