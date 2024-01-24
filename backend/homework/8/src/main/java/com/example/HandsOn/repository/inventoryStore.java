package com.example.HandsOn.repository;

import com.example.HandsOn.dto.vehicleDto;

import java.util.ArrayList;

import java.util.List;

public class inventoryStore {
    List<vehicleDto> vehicles=new ArrayList<>();
    public List<vehicleDto> vehicleList(){
        return vehicles;
    }
    public void add(vehicleDto vehicle){
        vehicles.add(vehicle);
    }


}
