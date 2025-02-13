package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("ProductId not found"));
        product.setIsDeleted(true);
        productRepository.save(product);
    }
}
