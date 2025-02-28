package com.example.homeworkwebsitebd.service;


import com.example.homeworkwebsitebd.entity.CartItem;
import com.example.homeworkwebsitebd.entity.ProductEntity;
import com.example.homeworkwebsitebd.repo.CartRepository;
import com.example.homeworkwebsitebd.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

//    public void addToCart(Long itemId, int quantity) {
//        CartItem cartItem = new CartItem();
//        cartItem.setProduct(productRepository.findById(itemId).orElse(null));
//        cartItem.setQuantity(quantity);
//        cartRepository.save(cartItem);
//    }
public void addToCart(Long itemId, int quantity) {
    System.out.println("🛒 Сохранение товара: itemId=" + itemId + ", quantity=" + quantity);

    // Найдём товар в базе
    ProductEntity productEntity = productRepository.findById(itemId).orElse(null);
    if (productEntity == null) {
        System.out.println("❌ Ошибка: Товар с itemId=" + itemId + " не найден!");
        return;
    }

    // Создадим запись в корзине
    CartItem cartItem = new CartItem();
    cartItem.setProduct(productEntity);
    cartItem.setQuantity(quantity);

    cartRepository.save(cartItem);
    System.out.println("✅ Товар добавлен в корзину!");
}



    public void removeFromCart(Long itemId) {
        cartRepository.deleteById(itemId);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }
}
