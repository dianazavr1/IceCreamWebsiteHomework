package com.example.homeworkwebsitebd.service;

import com.example.homeworkwebsitebd.entity.CartItem;
import com.example.homeworkwebsitebd.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public void addToCart(String name, int price) {
        CartItem cartItem = new CartItem();
        cartItem.setName(name);
        cartItem.setPrice(price);
        cartRepository.save(cartItem);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }

    public void addToCart(Long itemId) {
    }
}
