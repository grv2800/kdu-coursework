package com.example.miniproject.service;

import com.example.miniproject.entity.User;
import com.example.miniproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    UserRepository userRepository;
    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public void adduser(User user){
        log.info("user added successfully");
        userRepository.save(user);
    }
    public User getuser(String username){
        return userRepository.findUserByUsername(username);
    }
}
