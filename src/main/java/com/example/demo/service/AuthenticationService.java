package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.PredefinedRole;
import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.models.CustomUserDetails;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public void register(String username, String email, String password) {

    }

    public void login(UserLoginRequest request, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        User user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (user == null) {
            throw new RuntimeException("Email hoặc mật khẩu không chính xác");
        }

        String role = user.getRole() ? PredefinedRole.ADMIN_ROLE : PredefinedRole.CUSTOMER_ROLE;

        CustomUserDetails customUserDetails = new CustomUserDetails(request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
    }
}
