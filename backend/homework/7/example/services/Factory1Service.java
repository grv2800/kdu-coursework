package org.example.services;
import jakarta.annotation.PostConstruct;
import org.example.entities.Speaker;
import org.example.entities.Tyre;
import org.example.entities.Vehicle;
import org.example.inventoryConfig.inventoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Factory1Service extends VehicleService{
    @Autowired
    private TyreService tyreService;
@Autowired
    private SpeakerService speakerService;
    private Tyre tyre;
    private Speaker speaker;
    @Autowired
    private inventoryStore inventorystore;
////    @Autowired
////    Factory1Service(TyreService tyreService,SpeakerService speakerService) {
////        this.tyreService = tyreService;
////        this.speakerService = speakerService;
////    }
//
    @Override
    public double calculatePrice() {
        return basePrice*1.1 +tyre.getPrice()+speaker.getPrice();
    }
    @PostConstruct
    @Override
    public void createVehicle() {
        tyre=tyreService.generateTyre("MRF");
        speaker=speakerService.generateSpeaker("Bose");
        Vehicle vehicle=new Vehicle(speaker,tyre,calculatePrice(),"factory1");
        inventorystore.addVehicle(vehicle);
    }
}
