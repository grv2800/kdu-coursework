package com.JPA.JPA.repository;

import com.JPA.JPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    Page<User> findAll(Pageable pageable);
    @Modifying
    @Query(value = "UPDATE users SET username = :username WHERE id = :userId", nativeQuery = true)
    int updateUserUsername(UUID userId);
}
