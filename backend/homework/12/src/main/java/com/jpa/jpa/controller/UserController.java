package com.JPA.JPA.controller;


import com.JPA.JPA.entity.User;
import com.JPA.JPA.exception.UserNotFoundException;
import com.JPA.JPA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/postUser")
    public User createUser(@RequestBody User user) {
       log.info("user added");
        return userService.createUser(user);
    }
    @GetMapping("/findAllUsers")
    public Page<User> findAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "50") int size) {
        page = Math.max(page, 0);
        size = Math.min(Math.max(size, 1), 50);

        Pageable pageable = PageRequest.of(page, size);
        return userService.findAllUsers(pageable);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUserUsername(@PathVariable UUID userId) {
        try {
            log.info("user updated successfully");
            userService.updateUserUsername(userId);
            return ResponseEntity.ok("Username updated successfully for user with ID " + userId);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
