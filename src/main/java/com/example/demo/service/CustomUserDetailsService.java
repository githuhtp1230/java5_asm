package com.example.demo.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.models.CustomUserDetails;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String maNV) throws UsernameNotFoundException {
        // User nhanVien = nhanVienRepository.findByMa(maNV)
        // .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // return new CustomUserDetails(
        // nhanVien.getTaiKhoan(),
        // nhanVien.getMatKhau(),
        // List.of(new SimpleGrantedAuthority("ROLE_" + PredefinedRole.ADMIN_ROLE)),
        // nhanVien.getTen());

        // System.out.println(maNV);

        // return new CustomUserDetails

        throw new RuntimeException("Hello");
    }
}
