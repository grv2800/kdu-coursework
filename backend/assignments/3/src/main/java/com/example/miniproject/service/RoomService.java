package com.example.miniproject.service;

import com.example.miniproject.entity.House;
import com.example.miniproject.entity.Room;
import com.example.miniproject.exception.HouseNotFoundException;
import com.example.miniproject.repository.HouseRepository;
import com.example.miniproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    HouseRepository houseRepository;
    RoomRepository roomRepository;
    @Autowired
    RoomService(HouseRepository houseRepository,RoomRepository roomRepository){
        this.houseRepository=houseRepository;
        this.roomRepository=roomRepository;
    }

    public Room addRoomToHouse(String houseId, String roomName) throws HouseNotFoundException {
        // Find the house by houseId
        Optional<House> optionalHouse = houseRepository.findById(Integer.parseInt(houseId));
        if (optionalHouse.isEmpty()) {
            // Handle the case where the house with the given ID is not found
            throw new HouseNotFoundException("House with ID " + houseId + " not found");
        }

        House house = optionalHouse.get();

        // Create a new room
        Room room = new Room();
        room.setRoomName(roomName);
        room.setHouse(house); // Set the house for the room

        // Save the room
        room = roomRepository.save(room);

        // Add the room to the house
        house.getRooms().add(room);
        houseRepository.save(house);

        return room;
    }
}
