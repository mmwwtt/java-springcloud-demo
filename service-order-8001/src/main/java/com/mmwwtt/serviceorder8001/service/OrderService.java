package com.mmwwtt.serviceorder8001.service;


import com.mmwwtt.common.entity.Order;

public interface OrderService {
    Order createOrder(String productId, String userId);
}
