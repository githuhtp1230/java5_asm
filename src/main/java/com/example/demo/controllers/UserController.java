package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final HttpServletRequest httpServletRequest;
    private final UserService userService;

    @GetMapping("admin/users/{userId}/delete")
    public String deleteProduct(Model model, @PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("admin/users/{userId}/edit")
    public String editProduct(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("user", userService.getUserById(userId));
        return "admin/user";
    }

    @PostMapping("admin/users")
    public String saveUsers(Model model, @ModelAttribute User user, @RequestParam("action") String action) {
        // productService.saveProduct(product);
        if (action.equals("add")) {
            userService.saveUser(user);
        } else if (action.equals("update")) {
            userService.updateUser(user);
        }
        return "redirect:/admin/products";
    }

    @ModelAttribute("isEditing")
    public boolean getIsEditing() {
        if (!httpServletRequest.getRequestURL().toString().contains("edit")) {
            return false;
        }
        return true;
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
