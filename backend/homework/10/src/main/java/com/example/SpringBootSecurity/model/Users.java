package com.example.SpringBootSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users{
    private String userName;
    private String password;
    private String email;
    private String role;
}