package com.example.miniproject.mapper;

import com.example.miniproject.dto.request.UserRegisterRequest;
import com.example.miniproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserRegisterMapper {
        PasswordEncoder passwordEncoder;
        @Autowired
        UserRegisterMapper(PasswordEncoder passwordEncoder){
          this.passwordEncoder=passwordEncoder;
        }
        public User userMapper(UserRegisterRequest userDTO) {
            User user=new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setName(userDTO.getName());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmailId(userDTO.getEmailId());
            return user;
        }
}
