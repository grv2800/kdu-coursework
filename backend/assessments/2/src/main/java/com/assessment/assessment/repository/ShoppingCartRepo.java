package com.assessment.assessment.repository;

import com.assessment.assessment.entity.Product;
import com.assessment.assessment.entity.ShoppingCart;
import com.assessment.assessment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
   Optional<ShoppingCart> findByProductAndUser(Product product, User user);
   List<ShoppingCart> findByUser(User user);
   ShoppingCart findUser(User user);
}

