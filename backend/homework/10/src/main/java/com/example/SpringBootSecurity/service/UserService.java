package com.example.SpringBootSecurity.service;

import com.example.SpringBootSecurity.dao.UserDAO;
import com.example.SpringBootSecurity.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public void addUser(Users user){
        userDAO.addUser(user);
    }
    public Users getUser(String username){
       return userDAO.getUser(username);
    }
    public List<Users> getUserList(){
        return userDAO.getUserList();
    }
}

