package com.example.homeworkwebsitebd.controller;

import com.example.homeworkwebsitebd.dto.CartRequest;
import com.example.homeworkwebsitebd.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

//    @PostMapping(value = "/add", consumes = "application/json")
//    public ResponseEntity<String> addToCart(@RequestBody Map<String, Long> request) {
//        Long itemId = request.get("itemId");
//        if (itemId == null) {
//            return ResponseEntity.badRequest().body("Ошибка: itemId не передан");
//        }
//        cartService.addToCart(itemId);
//        return ResponseEntity.ok("Товар добавлен в корзину");
//    }
@PostMapping(value = "/add", consumes = "application/json")
public ResponseEntity<String> addToCart(@RequestBody CartRequest request) {
    Long itemId = request.getItemId();
    if (itemId == null) {
        return ResponseEntity.badRequest().body("Ошибка: itemId не передан");
    }
    cartService.addToCart(itemId);
    return ResponseEntity.ok("Товар добавлен в корзину");
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
