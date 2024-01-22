package org.example.Services;
import org.example.entities.Speaker;
import org.springframework.stereotype.Component;

@Component
public class SpeakerService {
    /**
     * method to generate speaker
     * @return Speaker
     */
    public Speaker generateSpeaker(String brand) {
        int price;
        if(brand.equals("Bose")) price= 1200;
        else price=700;
        return new Speaker(brand, price);
    }
}
