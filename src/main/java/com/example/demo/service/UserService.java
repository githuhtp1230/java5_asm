package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    public void updateUser(User user) {
        user.setIsDeleted(false);
        userRepository.save(user);
    }

    public User getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public void saveUser(User user) {
        user.setIsDeleted(false);
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findUserByIsDeletedIsFalse();
    }
}
