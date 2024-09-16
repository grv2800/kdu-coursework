package com.assessment.assessment.repository;

import com.assessment.assessment.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID>{
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Optional<User> findUserByUsername(String username);
    Page<User> findAll(Pageable pageable);
}
