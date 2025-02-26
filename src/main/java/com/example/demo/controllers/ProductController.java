package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("admin/products/{productId}/delete")
    public String deleteProduct(Model model, @PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin/products";
    }

    @GetMapping("admin/products/{productId}/edit")
    public String editProduct(Model model, @PathVariable("productId") int productId) {
        model.addAttribute("product", productService.getProductById(productId));
        return "admin/product";
    }

    @PostMapping("admin/products")
    public String saveProduct(Model model, @ModelAttribute Product product, @RequestParam("action") String action) {
        // productService.saveProduct(product);
        if (action.equals("add")) {
            productService.saveProduct(product);
        } else if (action.equals("update")) {
            productService.updateProduct(product);
        }
        return "redirect:/admin/products";
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productRepository.findProductsByIsDeletedIsFalse();
    }

    @ModelAttribute("isEditing")
    public boolean getIsEditing() {
        if (!httpServletRequest.getRequestURL().toString().contains("edit")) {
            return false;
        }
        return true;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
}
