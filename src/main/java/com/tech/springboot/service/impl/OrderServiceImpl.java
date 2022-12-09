package com.tech.springboot.service.impl;

import com.tech.springboot.dto.OrderRequest;
import com.tech.springboot.dto.OrderResponse;
import com.tech.springboot.entity.Order;
import com.tech.springboot.entity.Payment;
import com.tech.springboot.exception.PaymentException;
import com.tech.springboot.repository.OrderRepository;
import com.tech.springboot.repository.PaymentRepository;
import com.tech.springboot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("IN-PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type is not supported!");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        return OrderResponse
                .builder()
                .orderTrackingNumber(order.getOrderTrackingNumber())
                .status(order.getStatus())
                .message("SUCCESS")
                .build();

    }
}
