package com.example.HandsOn.service;

import com.example.HandsOn.dto.vehicleDto;

public interface vehicleInterface {
    void addVehicle(vehicleDto vehicle);
    vehicleDto getVehicle(int id);
    void updateVehicle(vehicleDto vehicleDto,int id);
    void deleteVehicle(int id);
    vehicleDto mostExpensiveVehicle();
    vehicleDto leastExpensiveVehicle();
}

