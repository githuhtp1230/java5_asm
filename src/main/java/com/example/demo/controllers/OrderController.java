package com.example.demo.controllers;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @GetMapping("users/{userId}/orders")
    public String ordersOfUser(Model model, @PathVariable Integer userId) {
        model.addAttribute("orders", orderDetailRepository.findOrderDetailByUserId(userId));
        return "admin/orders-of-user";
    }

}
