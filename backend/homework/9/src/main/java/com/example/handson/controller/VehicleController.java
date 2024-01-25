package com.example.handson.controller;
import com.example.handson.exception.BadRequestException;
import com.example.handson.exception.VehicleNotFoundException;
import com.example.handson.dto.VehicleDto;
import com.example.handson.entity.Vehicle;
import com.example.handson.service.GenerateVehicles;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mapper.VehicleMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class VehicleController {
  
    GenerateVehicles vehicles=new GenerateVehicles();
    @PostMapping("/User")
    public ResponseEntity<String> addVehicle(@RequestBody @Valid VehicleDto vehicledto){
        Vehicle vehicle= VehicleMapper.mapDtoToVehicle(vehicledto);
        vehicles.addVehicle(vehicle);
        log.info("vehicle added successfully");
        return ResponseEntity.ok("vehicle added successfully");
    }

    @GetMapping("/User/{id}")
    public VehicleDto getVehicle(@PathVariable String id) throws BadRequestException{
        if(!id.chars().allMatch(Character::isDigit)) {
            log.error("Invalid ID");
            throw new BadRequestException("Vehicle not found");
        }
           VehicleDto vehicle= vehicles.getVehicle(Integer.parseInt(id));
           log.info("vehicle found");
           return vehicle;

    }

    @PutMapping("/search/User/{id}")
    public ResponseEntity<String> updateVehicle(@RequestBody VehicleDto vehicle, @PathVariable String id) throws VehicleNotFoundException{
        if(!id.chars().allMatch(Character::isDigit)) {
            log.error("invalid id");
            throw new VehicleNotFoundException("Bad Request, invalid ID");
        }

            vehicles.updateVehicle(vehicle, Integer.parseInt(id));
            log.info("vehicle updated successfully");
            return ResponseEntity.ok("vehicle updated successfully");
    }

    @DeleteMapping("/User/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String id) throws VehicleNotFoundException{
        if(!id.chars().allMatch(Character::isDigit)) {
            log.error("invalid ID");
            throw new VehicleNotFoundException("vehicle not found");
        }

            vehicles.deleteVehicle(Integer.parseInt(id));
            log.info("vehicle deleted successfully");
            return ResponseEntity.ok("vehicle deleted successfully");
    }
    @GetMapping("/User/mostExpensive")
    public Vehicle mostExpensive(){
        log.info("Returning most expensive vehicle");
        return vehicles.mostExpensiveVehicle();
    }
    @GetMapping("/User/leastExpensive")
    public Vehicle leastExpensive(){

        log.info("Returning least expensive vehicle");
        return vehicles.leastExpensiveVehicle();
    }
}
