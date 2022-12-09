package com.tech.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponse {
    private String orderTrackingNumber;
    private String status;
    private String message;
}
