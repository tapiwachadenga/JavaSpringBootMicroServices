package com.mr.tApps.productservice.exception;

import com.mr.tApps.productservice.payload.response.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductServiceCustomException extends RuntimeException{

    private ErrorCode errorCode;

    public ProductServiceCustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
