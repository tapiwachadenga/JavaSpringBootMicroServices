package com.mr.tApps.orderservice.controller;

import com.mr.tApps.orderservice.payload.request.OrderRequest;
import com.mr.tApps.orderservice.payload.response.OrderResponse;
import com.mr.tApps.orderservice.service.OrderService;
import com.mr.tApps.orderservice.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {

        LOGGER.info("OrderController | placeOrder is called");

        LOGGER.info("OrderController | placeOrder | orderRequest: {}", orderRequest.toString());

        long orderId = orderService.placeOrder(orderRequest);
        LOGGER.info("Order Id: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId) {

        LOGGER.info("OrderController | getOrderDetails is called");

        OrderResponse orderResponse
                = orderService.getOrderDetails(orderId);

        LOGGER.info("OrderController | getOrderDetails | orderResponse : " + orderResponse.toString());

        return new ResponseEntity<>(orderResponse,
                HttpStatus.OK);
    }
}
