package com.assessment.assessment.service;

import com.assessment.assessment.entity.CartItem;
import com.assessment.assessment.entity.Order;
import com.assessment.assessment.entity.OrderItem;
import com.assessment.assessment.entity.ShoppingCart;
import com.assessment.assessment.repository.OrderRepo;
import com.assessment.assessment.repository.ShoppingCartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private ShoppingCartRepo cartRepository;

    @Transactional
    public void placeOrder(Order orderRequest) {
        ShoppingCart cart = cartRepository.findById(orderRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(orderRequest.getShippingAddress());

        double totalAmount = 0;

        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
            order.getItems().add(orderItem);
        }

        order.setTotalAmount(totalAmount);

        orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        }
}
