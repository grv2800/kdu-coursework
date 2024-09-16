package com.assessment.assessment.controller;

import com.assessment.assessment.entity.User;
import com.assessment.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user/login")

    public ResponseEntity<String> login(){
        return new ResponseEntity<>("user logged in succesfully", HttpStatus.CREATED);
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>("user is now registered", HttpStatus.CREATED);
    }
    @GetMapping("/findAllUsers")
    public Page<User> findAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "50") int size) {
        page = Math.max(page, 0);
        size = Math.min(Math.max(size, 1), 50);

        Pageable pageable = PageRequest.of(page, size);
        return userService.findAllUsers(pageable);
    }
}
