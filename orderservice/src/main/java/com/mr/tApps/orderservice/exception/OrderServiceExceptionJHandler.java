package com.mr.tApps.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.mr.tApps.orderservice.payload.response.ErrorResponse;

@ControllerAdvice
public class OrderServiceExceptionJHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(OrderServiceCustomException.class)
    public ResponseEntity<ErrorResponse> orderServiceExceptionJHandler(OrderServiceCustomException exception){
        new ErrorResponse();
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .build(), HttpStatus.NOT_FOUND
        );
    }
}
