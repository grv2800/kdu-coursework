package org.example.services;

import org.example.inventoryConfig.inventoryStore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("Prototype")
public abstract class VehicleService {
    double basePrice=1100.0;

    public abstract double calculatePrice();
    public abstract void createVehicle();
}
