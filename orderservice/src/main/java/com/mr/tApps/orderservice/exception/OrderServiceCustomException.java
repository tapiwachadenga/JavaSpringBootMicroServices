package com.mr.tApps.orderservice.exception;

import com.mr.tApps.orderservice.util.ErrorCode;
import com.mr.tApps.orderservice.util.OrderStatus;

public class OrderServiceCustomException extends RuntimeException{

    private ErrorCode errorCode;
    private OrderStatus orderStatus;

    public OrderServiceCustomException(String message, ErrorCode errorCode, OrderStatus orderStatus) {
        super(message);
        this.errorCode = errorCode;
        this.orderStatus = orderStatus;
    }
}
