package com.JPA.JPA.service;

import com.JPA.JPA.entity.User;
import com.JPA.JPA.exception.UserNotFoundException;
import com.JPA.JPA.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public void updateUserUsername(UUID userId)throws UserNotFoundException {
        int rowsAffected = userRepository.updateUserUsername(userId);
        if (rowsAffected == 0) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

}
