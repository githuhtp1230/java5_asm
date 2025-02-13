package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    @PostMapping("products/{productId}/delete")
    public String deleteProduct(Model model) {
        return "admin/product";
    }

}
