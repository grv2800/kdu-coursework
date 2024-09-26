package org.example.services;
import org.example.entities.Speaker;
import org.springframework.stereotype.Component;

@Component
public class SpeakerService {
    /**
     * method to generate speaker
     * @return Speaker
     */
    public Speaker generateSpeaker(String brand) {
        double price;
        if(brand.equals("Bose")) price= 1200.0;
        else price=700.0;
        return new Speaker(brand, price);
    }
}
