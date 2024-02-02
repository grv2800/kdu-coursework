package com.assessment.assessment.repository;

import com.assessment.assessment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo  extends JpaRepository<Order, Long> {
}
