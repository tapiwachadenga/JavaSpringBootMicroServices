package com.mr.tApps.orderservice.service;

import com.mr.tApps.orderservice.payload.request.OrderRequest;
import com.mr.tApps.orderservice.payload.response.OrderResponse;

public interface OrderService {

    Long placeOrder(OrderRequest orderRequest);
    OrderResponse getOrderDetails(Long orderId);
}
