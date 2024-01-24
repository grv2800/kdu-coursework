package com.example.HandsOn.controller;

import com.example.HandsOn.dto.vehicleDto;
import com.example.HandsOn.service.generateVehicles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class vehicleController {

    generateVehicles vehicles=new generateVehicles();
    @PostMapping("/User")
    public ResponseEntity<String> addVehicle(@RequestBody vehicleDto vehicle){
        vehicles.addVehicle(vehicle);
        return ResponseEntity.ok("vehicle added successfully");
    }

    @GetMapping("/User/{id}")
    public vehicleDto getVehicle(@PathVariable int id){
        return vehicles.getVehicle(id);
    }
    @PutMapping("/search/User/{id}")
    public ResponseEntity<String> updateVehicle(@RequestBody vehicleDto vehicle,@PathVariable int id){
        vehicles.updateVehicle(vehicle,id);
        return ResponseEntity.ok("vehicle updated successfully");
    }
    @DeleteMapping("/User/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id){
        vehicles.deleteVehicle(id);
        return ResponseEntity.ok("vehicle deleted successfully");
    }
}
