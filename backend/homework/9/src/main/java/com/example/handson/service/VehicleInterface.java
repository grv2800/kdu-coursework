package com.example.handson.service;

import com.example.handson.dto.VehicleDto;
import com.example.handson.entity.Vehicle;

public interface VehicleInterface {
    void addVehicle(Vehicle vehicle);
    VehicleDto getVehicle(int id);
    void updateVehicle(VehicleDto vehicle, int id);
    void deleteVehicle(int id);
    Vehicle mostExpensiveVehicle();
    Vehicle leastExpensiveVehicle();
}

