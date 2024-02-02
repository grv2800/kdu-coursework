package com.assessment.assessment.controller;

import com.assessment.assessment.entity.Inventory;
import com.assessment.assessment.exception.InventoryNotFound;
import com.assessment.assessment.exception.ProductNotFoundException;
import com.assessment.assessment.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getInventory")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/getInventory/{id}")
    public Optional<Inventory> getInventoryById(@PathVariable Long id) throws InventoryNotFound {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping("/restockInventory/{productId}")
    public ResponseEntity<String> restockInventory(@PathVariable Long productId) throws InventoryNotFound, ProductNotFoundException {
        inventoryService.restockInventory(productId);
        return new ResponseEntity<>("inventory restocked", HttpStatus.CREATED);
    }
}
