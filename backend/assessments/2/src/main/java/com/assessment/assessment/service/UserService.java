package com.assessment.assessment.service;

import com.assessment.assessment.entity.User;
import com.assessment.assessment.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepo userRepo;
    public void addUser(User user){
        log.info("user added in repository");
        userRepo.save(user);
    }
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findUserByUsername(username);
        return userOptional.orElse(null);
    }
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

}
