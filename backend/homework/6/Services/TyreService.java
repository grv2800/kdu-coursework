package org.example.Services;

import org.example.entities.Tyre;

import org.springframework.stereotype.Component;

@Component
public class TyreService {
    /**
     * method to generate Tyres
     * @param brand
     * @return
     */
    public Tyre generateTyre(String brand) {
        int price ;
        if(brand.equals("MRF")) price=2000;
        else price=1000;
        return new Tyre(brand, price);
    }
}
