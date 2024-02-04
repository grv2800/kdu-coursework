package com.example.HandsOn.service;

import com.example.HandsOn.dto.vehicleDto;
import com.example.HandsOn.repository.inventoryStore;

import java.util.Comparator;

public class generateVehicles implements vehicleInterface{
    inventoryStore inventoryStore=new inventoryStore();

    @Override
    public void addVehicle(vehicleDto vehicle) {
        inventoryStore.add(vehicle);
    }
    @Override
    public vehicleDto getVehicle(int id) {
       return inventoryStore.vehicleList().get(id);
    }

    @Override
    public void updateVehicle(vehicleDto vehicleDto,int id) {
        inventoryStore.vehicleList().set(id,vehicleDto);
    }

    @Override
    public void deleteVehicle(int id) {
        inventoryStore.vehicleList().remove(id);
    }

    @Override
    public vehicleDto mostExpensiveVehicle() {
        return inventoryStore.vehicleList().stream().max(Comparator.comparing(vehicleDto::getPrice)).orElse(null);
    }

    @Override
    public vehicleDto leastExpensiveVehicle() {
        return inventoryStore.vehicleList().stream().min(Comparator.comparing(vehicleDto::getPrice)).orElse(null);
    }
}
