package com.example.homeworkwebsitebd.config;

import com.example.homeworkwebsitebd.entity.ProductEntity;
import com.example.homeworkwebsitebd.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) { // Добавляем, если таблица пустая
            ProductEntity product1 = new ProductEntity("Мороженое", 150);
            ProductEntity product2 = new ProductEntity("Шоколад", 200);
            productRepository.save(product1);
            productRepository.save(product2);
        }
    }
}

