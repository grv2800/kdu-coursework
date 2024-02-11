package com.example.miniproject.service;

import com.example.miniproject.dto.request.AddUserRequest;
import com.example.miniproject.dto.request.HouseRequest;
import com.example.miniproject.dto.response.HouseResponse;
import com.example.miniproject.entity.House;
import com.example.miniproject.entity.User;
import com.example.miniproject.exception.HouseNotFoundException;
import com.example.miniproject.exception.UserNotFoundException;
import com.example.miniproject.repository.HouseRepository;
import com.example.miniproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HouseService {

     HouseRepository houseRepository;
     UserRepository userRepository;
    @Autowired
    HouseService(HouseRepository houseRepository,UserRepository userRepository)
    {
        this.houseRepository=houseRepository;
        this.userRepository=userRepository;
    }

    public HouseResponse addHouse(HouseRequest houseRequest) {
        House house=new House();
        house.setAddress(houseRequest.getAddress());
        house.setName(houseRequest.getName());
        house= houseRepository.save(house);
        return new HouseResponse("house created successfully",house);
    }
    public String addUserToHouse(String houseId, AddUserRequest addUserRequest)throws HouseNotFoundException,UserNotFoundException {
        // Retrieve the house from the repository
        House house = houseRepository.findById(Integer.parseInt(houseId))
                .orElseThrow(() -> new HouseNotFoundException("House not found"));

//        if (!house.getAdminUsers().contains(addUserRequest.getAdminUsername())) {
//            throw new UnauthorizedAccessException("Only the admin can add users to the house");
//        }

        // Check if the user to be added already exists
        User userToAdd = userRepository.findUserByUsername(addUserRequest.getUsername());
        if (userToAdd == null) {
            throw new UserNotFoundException("User not found");
        }

        // Add the user to the list of users in the house
        house.getUsers().add(userToAdd);

        // Save the updated house
        houseRepository.save(house);

        return "User added Successfully";
    }
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public String updateHouseAddress(String houseId, String newAddress) throws HouseNotFoundException {
        House house = houseRepository.findById(Integer.parseInt(houseId))
                .orElseThrow(() -> new HouseNotFoundException("House not found"));

        house.setAddress(newAddress);
        houseRepository.save(house);

        return "House address updated successfully";
    }

    public House getDevices(String houseId){
        log.info("list of devices and rooms succesfully retrieved");
       return houseRepository.getById(Integer.parseInt(houseId));
    }
}

