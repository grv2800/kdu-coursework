package com.example.SpringBootSecurity.controller;

import com.example.SpringBootSecurity.model.Users;
import com.example.SpringBootSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        log.info("getting user list");
        List<Users> users = userService.getUserList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        log.info("getting users");
        Users user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/postUsers")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
        log.info("adding users to list");
        userService.addUser(user);
        return ResponseEntity.ok("user added");
    }

}
