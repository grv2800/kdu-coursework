package com.example.SpringBootSecurity.config;

import com.example.SpringBootSecurity.model.Users;
import com.example.SpringBootSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class UserConfig implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userService.getUser(username);
        String userName=null;
        String userPassword=null;
        List<GrantedAuthority> authorities=null;
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        else {
            userName=user.getUserName();
            userPassword=user.getPassword();
            authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
        }
        return new User(userName,userPassword,authorities);
    }
}
