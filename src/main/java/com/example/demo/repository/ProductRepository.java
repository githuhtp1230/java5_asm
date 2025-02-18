package com.example.demo.repository;

import com.example.demo.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findProductsByCategoryId(Integer categoryId, Pageable pageable);

    List<Product> findProductsByIsDeletedIsFalse();
}
