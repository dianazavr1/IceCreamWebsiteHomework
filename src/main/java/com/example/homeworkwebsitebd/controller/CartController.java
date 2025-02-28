package com.example.homeworkwebsitebd.controller;

import com.example.homeworkwebsitebd.dto.CartItemRequest;
import com.example.homeworkwebsitebd.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "cart"; // Имя Thymeleaf-шаблона (cart.html)
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, Object> request) {
        System.out.println("Полученные данные: " + request);

        Object itemId = request.get("itemId");
        Object quantity = request.get("quantity");

        if (itemId == null || quantity == null) {
            return ResponseEntity.badRequest().body("Ошибка: itemId или quantity не переданы");
        }

        cartService.addToCart(Long.parseLong(itemId.toString()), Integer.parseInt(quantity.toString()));
        return ResponseEntity.ok("Товар добавлен");
    }


    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long itemId) {
        cartService.removeFromCart(itemId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
