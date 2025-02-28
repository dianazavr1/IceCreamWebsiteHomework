package com.example.homeworkwebsitebd.service;

import com.example.homeworkwebsitebd.entity.ProductEntity;
import com.example.homeworkwebsitebd.repo.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void initProducts() {
        if (productRepository.count() == 0) {
            ProductEntity product1 = new ProductEntity( "Мороженое", 150);
            ProductEntity product2 = new ProductEntity( "Шоколад", 200);
            productRepository.saveAll(List.of(product1, product2));
            System.out.println("✅ Добавлены тестовые товары!");
        }
    }
}

