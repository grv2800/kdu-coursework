package com.example.miniproject.service;


import com.example.miniproject.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    TokenRepository tokenRepository;
    @Autowired
    AuthenticationService(TokenRepository tokenRepository){
        this.tokenRepository=tokenRepository;
    }
    public boolean isValidToken(String token) {
            return token != null && !token.isEmpty() && tokenRepository.existsByTokenValue(token);
    }
}
