package org.example.Services;

import jakarta.annotation.PostConstruct;

import org.example.entities.Speaker;
import org.example.entities.Tyre;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class VehicleService {
    @Autowired
    private TyreService tyreService;
    @Autowired
    private SpeakerService speakerService;

    private List<Vehicle> vehicles;

    /**
     * Method to generateVehicle List , declared as PostConstruct.
     */
    @PostConstruct
    public void generateVehicles(){
        vehicles = new ArrayList<>();
        Tyre tyre = tyreService.generateTyre("MRF");
        Speaker speaker = speakerService.generateSpeaker("Sony");
        Tyre tyre2 = tyreService.generateTyre("Bridgestone");
        Speaker speaker2 = speakerService.generateSpeaker("Bose");

        for(int i=0;i<10;i++){
          Vehicle vehicle;
            if(i%4==0)  {
                int vehiclePrice = tyre.getPrice() + speaker.getPrice() + 1500;
                 vehicle = new Vehicle(speaker,tyre, vehiclePrice);
            }
            else if(i%4==1){
                int vehiclePrice = tyre2.getPrice() + speaker.getPrice() + 1500;
                 vehicle = new Vehicle(speaker,tyre2, vehiclePrice);
            }
            else if(i%4==2){
                int vehiclePrice = tyre2.getPrice() + speaker2.getPrice() + 1500;
                 vehicle = new Vehicle(speaker2,tyre2, vehiclePrice);
            }
            else{
                int vehiclePrice = tyre.getPrice() + speaker2.getPrice() + 1500;
                 vehicle = new Vehicle(speaker2,tyre, vehiclePrice);
            }
            vehicles.add(vehicle);
        }

    }
    public Vehicle findMostExpensiveVehicle(){
        return vehicles.stream()
                .max(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }


}
