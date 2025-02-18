package com.example.demo.controllers;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;

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
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("users")
    public String user(Model model) {
        // model.addAttribute("users", userRepository.findAll());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", new User());
        model.addAttribute("isEditing", false);
        return "admin/user";
    }

    @GetMapping("products")
    public String product(Model model) {
        model.addAttribute("products", productRepository.findProductsByIsDeletedIsFalse());
        model.addAttribute("product", new Product());
        model.addAttribute("isEditing", false);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product";
    }
}
