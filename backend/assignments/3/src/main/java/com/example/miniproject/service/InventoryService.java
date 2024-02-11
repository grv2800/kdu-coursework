package com.example.miniproject.service;

import com.example.miniproject.dto.request.InventoryRequest;
import com.example.miniproject.dto.response.InventoryResponse;
import com.example.miniproject.entity.Inventory;
import com.example.miniproject.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class InventoryService {

    InventoryRepository inventoryItemRepository;
    @Autowired
    InventoryService(InventoryRepository inventoryItemRepository){
        this.inventoryItemRepository=inventoryItemRepository;
    }

    public List<InventoryResponse> getAllInventoryItems() {
        List<Inventory> inventoryItems = inventoryItemRepository.findAll();
        return inventoryItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private InventoryResponse convertToDto(Inventory inventoryItem) {
        InventoryResponse inventoryItemDTO = new InventoryResponse();
        inventoryItemDTO.setKickstonId(inventoryItem.getKickstonId());
        inventoryItemDTO.setDeviceUsername(inventoryItem.getDeviceUsername());
        inventoryItemDTO.setDevicePassword(inventoryItem.getDevicePassword());
        return inventoryItemDTO;
    }

    public ResponseEntity<String> addItemToInventory(InventoryRequest inventoryRequest){
        Inventory inventory=new Inventory();
        inventory.setKickstonId(inventoryRequest.getKickstonId());
        inventory.setDeviceUsername(inventoryRequest.getDeviceUsername());
        inventory.setDevicePassword(inventoryRequest.getDevicePassword());
        inventory.setManufactureDateTime(LocalDateTime.parse(inventoryRequest.getManufactureDateTime()));
        inventory.setManufactureFactoryPlace(inventoryRequest.getManufactureFactoryPlace());
        inventoryItemRepository.save(inventory);
        return ResponseEntity.ok("item added to inventory");
    }
}
