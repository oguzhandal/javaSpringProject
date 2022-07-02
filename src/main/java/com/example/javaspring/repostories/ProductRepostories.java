package com.example.javaspring.repostories;

import com.example.javaspring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepostories extends JpaRepository<Product, Integer> {
    List<Product> findByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(String title, String detail);

    long countByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(String title, String detail);

    List<Product> findByPriceBetween(Double priceStart, Double priceEnd);

    long countByPriceBetween(Double priceStart, Double priceEnd);

}
