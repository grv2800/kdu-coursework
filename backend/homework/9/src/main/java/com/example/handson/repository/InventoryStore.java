package com.example.handson.repository;

import com.example.handson.entity.Vehicle;

import java.util.ArrayList;

import java.util.List;

public class InventoryStore {
    List<Vehicle> vehicles=new ArrayList<>();
    public List<Vehicle> vehicleList(){
        return vehicles;
    }
    public void add(Vehicle vehicle){
        vehicles.add(vehicle);
    }
}
