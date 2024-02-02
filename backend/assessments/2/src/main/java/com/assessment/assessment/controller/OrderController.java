package com.assessment.assessment.controller;

import com.assessment.assessment.entity.Order;
import com.assessment.assessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order orderRequest) {
        orderService.placeOrder(orderRequest);
        return ResponseEntity.ok("Order placed successfully");
    }
}
