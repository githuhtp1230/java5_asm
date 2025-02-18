package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;

    @GetMapping("cart")
    public String cart(Model model) {
        return "client/cart";
    }

    @PostMapping("cart/orders/{orderId}/products/{productId}/update-quantity")
    public String updateQuantity(@PathVariable int orderId, @PathVariable int productId, @RequestParam String action) {
        cartService.updateQuantityProductOfMyCart(orderId, productId, action);
        return "client/cart";
    }

    @GetMapping("cart/orders/{orderId}/products/{productId}/delete")
    public String getMethodName(@PathVariable int orderId, @PathVariable int productId) {
        cartService.deleteProductOfMyCart(orderId, productId);
        return "redirect:/cart";
    }

    @ModelAttribute("cart")
    public List<OrderDetail> getCart() {
        return orderDetailRepository.getCart(1);
    }
}
