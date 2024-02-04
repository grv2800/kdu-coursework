package com.example.miniproject.repository;

import com.example.miniproject.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
        boolean existsByTokenValue(String tokenValue);
}
