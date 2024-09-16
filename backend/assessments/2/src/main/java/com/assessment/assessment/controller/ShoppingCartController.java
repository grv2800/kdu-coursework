package com.assessment.assessment.controller;

import com.assessment.assessment.entity.ShoppingCart;
import com.assessment.assessment.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addCartItems")
    public ResponseEntity<String> addToCart(@RequestParam UUID userId,@RequestParam Long productId, @RequestParam int quantity) {
        shoppingCartService.addToCart(userId,productId, quantity);
        return ResponseEntity.ok("Product added to the cart successfully");
    }

    @GetMapping("/viewCartItems")
    public ResponseEntity<ShoppingCart> viewCart(@RequestParam UUID userId) {
        ShoppingCart cart = shoppingCartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/updateCartItems")
    public ResponseEntity<String> updateCart(@RequestParam UUID userId,@RequestParam Long productId, @RequestParam int quantity) {
        shoppingCartService.updateCart(userId,productId, quantity);
        return ResponseEntity.ok("Cart updated successfully");
    }

    @DeleteMapping("/removeCartItems")
    public ResponseEntity<String> removeFromCart(@RequestParam UUID userId,@RequestParam Long productId) {
        shoppingCartService.removeFromCart(userId,productId);
        return ResponseEntity.ok("Product removed from the cart");
    }
}

