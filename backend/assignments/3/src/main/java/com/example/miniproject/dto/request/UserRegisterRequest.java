package com.example.miniproject.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String emailId;

}

