package com.example.SpringBootSecurity.dao;

import com.example.SpringBootSecurity.model.Users;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    List<Users> UserList;
    public UserDAO(){
        this.UserList=new ArrayList<>();
    }
    public void addUser(Users user){
        UserList.add(user);
    }
    public Users getUser(String username){
        return UserList.stream().filter(user -> user.getUserName().equals(username)).findFirst().orElse(null);
    }
    public List<Users> getUserList(){
        return UserList;
    }
}
