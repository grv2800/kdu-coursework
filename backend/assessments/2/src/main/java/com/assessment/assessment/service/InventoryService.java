package com.assessment.assessment.service;

import com.assessment.assessment.entity.Inventory;
import com.assessment.assessment.entity.Product;
import com.assessment.assessment.exception.InventoryNotFound;
import com.assessment.assessment.exception.ProductNotFoundException;
import com.assessment.assessment.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ProductService productService;

    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) throws InventoryNotFound {
        if(inventoryRepo.findById(id).isEmpty())
        {
            throw new InventoryNotFound("Inventory not found with id: " + id);
        }
        return inventoryRepo.findById(id);
    }

    public void restockInventory(Long productId) throws InventoryNotFound, ProductNotFoundException {
        Optional<Product> optionalProduct = productService.getProductById(productId);
        Product product=optionalProduct.get();
        Inventory inventory = inventoryRepo.findByProduct(product)
                .orElseThrow(() -> new InventoryNotFound("Inventory not found for product with id: " + productId));

        product.setStockQuantity(10);

        productService.addProduct(product);
        inventoryRepo.save(inventory);
    }
}
