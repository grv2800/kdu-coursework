package org.example.services;

import org.example.entities.Tyre;

import org.springframework.stereotype.Component;

@Component
public class TyreService {
    public Tyre generateTyre(String brand) {
        double price ;
        if(brand.equals("MRF")) price=2000.0;
        else price=1000.0;
        return new Tyre(brand, price);
    }
}
