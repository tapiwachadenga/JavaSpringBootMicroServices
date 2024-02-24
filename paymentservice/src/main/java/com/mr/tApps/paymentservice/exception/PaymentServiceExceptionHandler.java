package com.mr.tApps.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
import com.mr.tApps.paymentservice.payload.ErrorResponse;
// import com.microservice.paymentservice.payload.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PaymentServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentServiceCustomException.class)
    public ResponseEntity<ErrorResponse> paymentServiceExceptionHandler(PaymentServiceCustomException exception){
        new ErrorResponse();
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .message(exception.getMessage())
                // .errorCode(exception.)
                .build(), HttpStatus.NOT_FOUND);
    }
}
