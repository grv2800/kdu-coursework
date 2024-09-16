package com.assessment.assessment.service;

import com.assessment.assessment.entity.CartItem;
import com.assessment.assessment.entity.Product;
import com.assessment.assessment.entity.ShoppingCart;
import com.assessment.assessment.entity.User;
import com.assessment.assessment.exception.ProductNotFoundException;
import com.assessment.assessment.repository.ProductRepo;
import com.assessment.assessment.repository.ShoppingCartRepo;
import com.assessment.assessment.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ShoppingCartService {


    private ShoppingCartRepo cartRepository;
    private ProductRepo productRepository;
    private UserRepo userRepo;
    @Autowired
    ShoppingCartService(ShoppingCartRepo cartRepository,ProductRepo productRepository,UserRepo userRepo){
        this.cartRepository=cartRepository;
        this.productRepository=productRepository;
        this.userRepo=userRepo;
    }

    public void addToCart(UUID userId,long productId, int quantity) {
        log.info("Adding product to the cart");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ShoppingCart cart = getOrCreateCart(userId);

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem());

        item.setProduct(product);
        item.setQuantity(item.getQuantity() + quantity);
        item.setShoppingCart(cart);

        cart.getItems().add(item);

        cartRepository.save(cart);

        log.info("Product added to the cart successfully");
    }

    public ShoppingCart getCart(UUID userId) {
        log.info("Retrieving shopping cart");
        return getOrCreateCart(userId);
    }

    private ShoppingCart getOrCreateCart(UUID userId) {
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            ShoppingCart cart = cartRepository.findUser(user);
            if (cart == null) {
                cart = new ShoppingCart();
                cart.setUser(user);
                return cartRepository.save(cart);
            } else {
                return cart;
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }


    public void updateCart(UUID userId,Long productId, int quantity) {
        log.info("Updating quantity of product in the cart");
        ShoppingCart cart = getOrCreateCart(userId);
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        cartRepository.save(cart);
        log.info("Cart updated successfully");
    }

    public void removeFromCart(UUID userId,Long productId) {
        log.info("Removing product from the cart");
        ShoppingCart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
        log.info("Product removed from the cart successfully");
    }
}
