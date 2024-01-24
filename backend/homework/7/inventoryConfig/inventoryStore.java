package org.example.inventoryConfig;
import org.example.entities.Vehicle;
import org.example.services.VehicleService;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class inventoryStore {
    private Map<String,List<Vehicle>> inventorystore=new HashMap<>();
    public void addVehicle(Vehicle vehicle){
        inventorystore.computeIfAbsent(vehicle.getFactory(),k->new ArrayList<>()).add(vehicle);
    }
    public Vehicle mostExpensiveVehicle(){
        return inventorystore.values().stream().flatMap(List::stream).max(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }
    public Vehicle leastExpensiveVehicle(){
        return inventorystore.values().stream().flatMap(List::stream).min(Comparator.comparing(Vehicle::getPrice)).orElse(null);

    }
}
