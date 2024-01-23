package org.example.services;
import jakarta.annotation.PostConstruct;
import org.example.entities.Speaker;
import org.example.entities.Tyre;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Factory1Service extends VehicleService{

    private TyreService tyreService;

    private SpeakerService speakerService;
    @Autowired
    Factory1Service(TyreService tyreService,SpeakerService speakerService){
        this.tyreService=tyreService;
        this.speakerService=speakerService;
    }
    Tyre tyre = tyreService.generateTyre("MRF");
    Speaker speaker = speakerService.generateSpeaker("Sony");

    @Override
    public double calculatePrice() {
        return basePrice*1.1 +tyre.getPrice()+speaker.getPrice();
    }
    @PostConstruct
    @Override
    public void createVehicle() {
        inventorystore.addVehicle(new Vehicle(speaker,tyre,calculatePrice(),"factory1"));
    }
}
