package com.tech.springboot.service;

import com.tech.springboot.dto.OrderRequest;
import com.tech.springboot.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
