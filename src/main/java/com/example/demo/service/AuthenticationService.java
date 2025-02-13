package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    public void register(String username, String email, String password) {

    }

    public void login(UserLoginRequest request, HttpServletResponse httpServletResponse) {
        User user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (user == null) {
            throw new RuntimeException("Email hoặc mật khẩu không chính xác");
        }
    }
}
