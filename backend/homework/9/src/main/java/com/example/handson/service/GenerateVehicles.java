package com.example.handson.service;
import com.example.handson.exception.NullPointException;
import com.example.handson.dto.VehicleDto;
import com.example.handson.entity.Vehicle;
import com.example.handson.repository.InventoryStore;
import mapper.VehicleMapper;

import java.util.Comparator;

public class GenerateVehicles implements VehicleInterface {
    InventoryStore inventoryStore=new InventoryStore();

    @Override
    public void addVehicle(Vehicle vehicle) {
        inventoryStore.add(vehicle);
    }
    @Override
    public VehicleDto getVehicle(int id) {
       return VehicleMapper.mapTovehicleDto(inventoryStore.vehicleList().get(id));
    }

    @Override
    public void updateVehicle(VehicleDto vehicleDto, int id) {
        inventoryStore.vehicleList().set(id, VehicleMapper.mapDtoToVehicle(vehicleDto));
    }

    @Override
    public void deleteVehicle(int id) {
        inventoryStore.vehicleList().remove(id);
    }

    @Override
    public Vehicle mostExpensiveVehicle() {
        if(inventoryStore.vehicleList().size()==0){
            throw new NullPointException("list is empty");
        }
        return inventoryStore.vehicleList().stream().max(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }

    @Override
    public Vehicle leastExpensiveVehicle() {
        if(inventoryStore.vehicleList().size()==0)
        {
            throw new NullPointException("list is empty");
        }
        return inventoryStore.vehicleList().stream().min(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }
}
