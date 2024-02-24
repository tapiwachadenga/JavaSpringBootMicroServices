package com.mr.tApps.orderservice.service;

import com.mr.tApps.orderservice.exception.OrderServiceCustomException;
import com.mr.tApps.orderservice.external.client.PaymentService;
import com.mr.tApps.orderservice.external.client.ProductService;
import com.mr.tApps.orderservice.model.Order;
import com.mr.tApps.orderservice.payload.response.PaymentResponse;
import com.mr.tApps.orderservice.util.ErrorCode;
import com.mr.tApps.orderservice.util.OrderStatus;
import com.mr.tApps.orderservice.payload.request.OrderRequest;
import com.mr.tApps.orderservice.payload.request.PaymentRequest;
import com.mr.tApps.orderservice.payload.response.OrderResponse;
import com.mr.tApps.orderservice.repository.OrderRepository;
import com.mr.tApps.productservice.payload.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
/*import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;*/
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mr.tApps.orderservice.util.PaymentStatus;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ProductService productService;

    // service called as kafka consumer
    private final PaymentService paymentService;

    // private final KafkaTemplate<String, PaymentRequest> kafkaTemplate;

    // @Value("${spring.kafka.topic.name}")
    // private String topic;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        LOGGER.info("OrderServiceImpl | placeOrder is called");

        //Order Entity -> Save the data with Status Order Created
        //Product Service - Block Products (Reduce the Quantity)
        //Payment Service -> Payments -> Success-> COMPLETE, Else
        //CANCELLED

        LOGGER.info("OrderServiceImpl | placeOrder | Placing Order Request orderRequest : " + orderRequest.toString());

        LOGGER.info("OrderServiceImpl | placeOrder | Calling productService through FeignClient");
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getProductQuantity());

        LOGGER.info("OrderServiceImpl | placeOrder | Creating Order with Status CREATED");

        Order order = Order.builder()
                .orderAmount(orderRequest.getProductTotalAmount())
                .orderStatus(orderRequest.getOrderStatus())
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .orderQuantity(orderRequest.getProductQuantity())
                .build();

        order = orderRepository.save(order);

        LOGGER.info("OrderServiceImpl | placeOrder | Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .paymentOrderId(order.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .paymentAmount(orderRequest.getProductTotalAmount())
                .paymentStatus(orderRequest.getPaymentStatus())
                .paymentRefNumber(orderRequest.getPaymentRefNumber())
                .build();

        /* send message to kafka broker
        Message<PaymentRequest> message = MessageBuilder
                .withPayload(paymentRequest)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);*/

        OrderStatus orderStatus = null;

        try {
            // paymentRequest object sent to kafka broker ** see above
            paymentService.doPayment(paymentRequest);
            LOGGER.info("OrderServiceImpl | placeOrder | Payment done Successfully. Changing the Oder status to PLACED");
            orderStatus = OrderStatus.PLACED;
        } catch (Exception e) {
            LOGGER.error("OrderServiceImpl | placeOrder | Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = OrderStatus.PAYMENT_FAILED;
        }

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);

        LOGGER.info("OrderServiceImpl | placeOrder | Order Places successfully with Order Id: {}", order.getOrderId());

        return order.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {

        LOGGER.info("OrderServiceImpl | getOrderDetails | Get order details for Order Id : {}", orderId);

        Order order
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderServiceCustomException("Order not found for the order Id:" + orderId,
                        ErrorCode.ORDER_NOT_FOUND,
                        OrderStatus.PAYMENT_FAILED));

        LOGGER.info("OrderServiceImpl | getOrderDetails | Invoking Product service to fetch the product for id: {}", order.getProductId());

        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/" + order.getProductId(),
                ProductResponse.class
        );

        LOGGER.info("OrderServiceImpl | getOrderDetails | Getting payment information form the payment Service");

        PaymentResponse paymentResponse
                = restTemplate.getForObject(
                "http://PAYMENT-SERVICE/payment/order/" + order.getOrderId(),
                PaymentResponse.class
        );

        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails
                .builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();

        OrderResponse.PaymentDetails paymentDetails
                = OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getPaymentStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse
                = OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getOrderAmount())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        LOGGER.info("OrderServiceImpl | getOrderDetails | orderResponse : " + orderResponse.toString());

        return orderResponse;
    }
}
