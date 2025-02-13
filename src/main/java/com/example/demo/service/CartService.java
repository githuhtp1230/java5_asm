package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final OrderDetailRepository orderDetailRepository;

    public void updateQuantityProductOfMyCart(int orderId, int productId, String action) {
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailByOrderIdAndProductId(orderId, productId);
        if (action.equals("increase")) {
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
        } else if (action.equals("decrease")) {
            orderDetail.setQuantity(orderDetail.getQuantity() - 1);
        }
        orderDetailRepository.save(orderDetail);
    }

    public void deleteProductOfMyCart(int orderId, int productId) {
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailByOrderIdAndProductId(orderId, productId);
        orderDetailRepository.delete(orderDetail);
    }
}
