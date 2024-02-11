package com.example.miniproject.controller;

import com.example.miniproject.dto.request.InventoryRequest;
import com.example.miniproject.dto.response.InventoryResponse;
import com.example.miniproject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class InventoryController {
    InventoryService inventoryService;

    @Autowired
    InventoryController(InventoryService inventoryService){
        this.inventoryService=inventoryService;
    }
    @GetMapping("/api/v1/inventory")
    public ResponseEntity<List<InventoryResponse>> getAllInventoryItems() {
        List<InventoryResponse> inventoryItems = inventoryService.getAllInventoryItems();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }
    @PostMapping("/api/v1/inventory")
    public ResponseEntity<String> addItemToInventory(@RequestBody InventoryRequest itemDTO) {
            inventoryService.addItemToInventory(itemDTO);
            String response = "Item added to inventory successfully";
            return ResponseEntity.ok(response);
    }
}
