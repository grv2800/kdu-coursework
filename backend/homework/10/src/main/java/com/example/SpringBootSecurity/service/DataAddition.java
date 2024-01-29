package com.example.SpringBootSecurity.service;
import com.example.SpringBootSecurity.dao.UserDAO;
import com.example.SpringBootSecurity.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class DataAddition implements CommandLineRunner {

        @Autowired
        UserDAO userDAO;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {
            userDAO.addUser(new Users("Gaurav", passwordEncoder.encode("new1234"),"grv28@gmail.com", "ROLE_ADMIN"));
            userDAO.addUser(new Users("Jenny",passwordEncoder.encode("new1234"), "jenny123@gmail.com","ROLE_BASIC"));
        }
}

