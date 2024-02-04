package com.example.miniproject.controller;

import com.example.miniproject.dto.request.AddUserRequest;
import com.example.miniproject.dto.request.HouseRequest;
import com.example.miniproject.dto.response.HouseListResponse;
import com.example.miniproject.dto.response.HouseResponse;
import com.example.miniproject.entity.House;
import com.example.miniproject.exception.HouseNotFoundException;
import com.example.miniproject.exception.UserNotFoundException;
import com.example.miniproject.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HouseController {
    HouseService houseService;
    @Autowired
    HouseController(HouseService houseService){
        this.houseService=houseService;
    }
    @PostMapping("/api/v1/house")
    public ResponseEntity<HouseResponse> registerHouse(@RequestBody HouseRequest houseRequest){
        HouseResponse houseResponse=houseService.addHouse(houseRequest);
        return ResponseEntity.ok(houseResponse);
    }
    @PostMapping("/api/v1/house/{houseId}/add-user")
    public ResponseEntity<String> addUserToHouse(
            @PathVariable String houseId,
            @RequestBody AddUserRequest addUserRequest) throws UserNotFoundException, HouseNotFoundException {
        String response = houseService.addUserToHouse(houseId, addUserRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/api/v1/house")
    public ResponseEntity<HouseListResponse> getHouses() {
        List<House> houseEntities = houseService.getAllHouses();
        HouseListResponse response = new HouseListResponse();
        response.setMessage("List of houses retrieved successfully");
        response.setHouses(houseEntities);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/api/v1/house")
    public ResponseEntity<String> updateHouseAddress(@RequestParam String houseId,
                                                            @RequestBody String newAddress) throws HouseNotFoundException {
        String response = houseService.updateHouseAddress(houseId,newAddress);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/house/{houseId}")
    public ResponseEntity<House> listRoomsAndDevices(@PathVariable String houseId){
        House houses=houseService.getDevices(houseId);
        return ResponseEntity.ok(houses);
    }

}
