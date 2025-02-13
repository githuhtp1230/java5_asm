package com.example.demo.controllers;

import com.example.demo.entity.Category;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class ClientHomeController {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CategoryRepository categoryRepository;

    private final static int PAGE_SIZE = 5;

    @GetMapping
    public String index(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page <= 0) {
            page = 1;
        }
        Page<Product> products = productRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE));

        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);

        return "client/home";
    }

    @GetMapping("/categories/{categoryId}")
    public String category(Model model, @PathVariable Integer categoryId, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        Page<Product> products = productRepository.findProductsByCategoryId(categoryId, pageable);
        model.addAttribute("products",
                products);

        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("categoryId", categoryId);

        return "client/home-category";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
