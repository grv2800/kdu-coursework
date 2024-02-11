package com.example.miniproject.controller;

import com.example.miniproject.dto.request.UserRegisterRequest;
import com.example.miniproject.entity.User;
import com.example.miniproject.mapper.UserRegisterMapper;
import com.example.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    UserRegisterMapper userRegisterMapper;
    UserService userService;
    @Autowired
    AuthController(UserRegisterMapper userRegisterMapper,UserService userService){
        this.userRegisterMapper=userRegisterMapper;
        this.userService=userService;
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("test login", HttpStatus.CREATED);
    }
    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest){
        User user=userRegisterMapper.userMapper(userRegisterRequest);
        userService.adduser(user);
//        UserRegisterRequest response = UserRegisterRequest.builder()
//                .jwtToken(token).name(userDetails.getUsername()).build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
        return ResponseEntity.ok("user registered successfully");
    }
}
