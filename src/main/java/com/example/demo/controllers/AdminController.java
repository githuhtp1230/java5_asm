package com.example.demo.controllers;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @GetMapping("user")
    public String user(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user";
    }

    @GetMapping("product")
    public String product(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/product";
    }
}
