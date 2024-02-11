package com.example.miniproject.config;

import com.example.miniproject.entity.User;
import com.example.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class UserConfig implements UserDetailsService {
        UserService userService;
        @Autowired
        UserConfig(UserService userService){
            this.userService=userService;
        }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user=userService.getuser(username);
            String userName=null;
            String userPassword=null;
            List<GrantedAuthority> authorities=null;
            if(user==null){
                throw new UsernameNotFoundException("user not found");
            }
            else {
                userName=user.getUsername();
                userPassword=user.getPassword();
                authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            return new org.springframework.security.core.userdetails.User(userName,userPassword,authorities);
        }
    }
