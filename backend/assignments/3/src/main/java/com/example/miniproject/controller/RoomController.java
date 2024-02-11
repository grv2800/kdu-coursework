package com.example.miniproject.controller;

import com.example.miniproject.dto.response.RoomResponse;
import com.example.miniproject.entity.Room;
import com.example.miniproject.exception.HouseNotFoundException;
import com.example.miniproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    RoomService roomService;
    @Autowired
    RoomController(RoomService roomService){
        this.roomService=roomService;
    }

    @PostMapping("/api/v1/room")
    public ResponseEntity<RoomResponse> addRoomToHouse(@RequestParam("houseId") String houseId,
            @RequestBody String roomName) throws HouseNotFoundException {

        Room room = roomService.addRoomToHouse(houseId,roomName);

        RoomResponse response = new RoomResponse();
        response.setMessage("Room added successfully");
        response.setRoom(room);

        return ResponseEntity.ok(response);
    }
}
