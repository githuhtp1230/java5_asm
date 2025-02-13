package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.CartService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;
    CartService cartService;
    AuthenticationService authenticationService;

    @GetMapping("register")
    public String register(Model model,
            @ModelAttribute(name = "userRegisterRequest") UserRegisterRequest userRegisterRequest) {
        model.addAttribute("userRegisterRequest", userRegisterRequest);
        return "authentication/register";
    }

    @GetMapping("login")
    public String login(Model model, @ModelAttribute("userLoginRequest") UserLoginRequest userLoginRequest) {
        return "authentication/login";
    }

    @PostMapping("login")
    public String loginPost(Model model, @ModelAttribute("userLoginRequest") UserLoginRequest userLoginRequest,
            HttpServletResponse httpServletResponse) {
        try {
            authenticationService.login(userLoginRequest, httpServletResponse);
            return "redirect:login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "authentication/login";
    }

    @PostMapping("register")
    public String registerPost(
            @Valid @ModelAttribute(name = "userRegisterRequest") UserRegisterRequest userRegisterRequest,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegisterRequest", userRegisterRequest);
            return "authentication/register";
        }
        return "redirect:/login";
        // return "authentication/register";
    }

}
