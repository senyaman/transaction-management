package com.tech.springboot.dto;

import com.tech.springboot.entity.Order;
import com.tech.springboot.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
