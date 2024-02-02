package com.assessment.assessment.service;

import com.assessment.assessment.entity.Product;
import com.assessment.assessment.exception.ProductNotFoundException;
import com.assessment.assessment.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) throws ProductNotFoundException{
        if( productRepo.findById(id).isEmpty())
        {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return productRepo.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product newProductData) {
        Optional<Product> product = productRepo.findById(id);
        Product existingProduct=product.get();
        existingProduct.setName(newProductData.getName());
        existingProduct.setDescription(newProductData.getDescription());
        existingProduct.setPrice(newProductData.getPrice());
        existingProduct.setStockQuantity(newProductData.getStockQuantity());
        return productRepo.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        Product existingProduct=product.get();
        productRepo.delete(existingProduct);
    }
}
