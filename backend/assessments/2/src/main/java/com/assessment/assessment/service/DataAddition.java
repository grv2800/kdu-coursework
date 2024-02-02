package com.assessment.assessment.service;

import com.assessment.assessment.entity.User;
import com.assessment.assessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataAddition implements CommandLineRunner {
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    @Autowired
    DataAddition(UserRepo userRepo,PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepo.save(new User(UUID.randomUUID(),"grv28",passwordEncoder.encode("1234"),"GauravMehta","grv28@gamil.com","ROLE_ADMIN"));
        userRepo.save(new User(UUID.randomUUID(),"jenny02",passwordEncoder.encode("1234"),"Jenny","jenny02@gamil.com","ROLE_CUSTOMER"));
    }
}