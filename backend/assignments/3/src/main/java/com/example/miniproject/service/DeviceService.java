package com.example.miniproject.service;

import com.example.miniproject.dto.request.AddDeviceToHouseRequest;
import com.example.miniproject.dto.request.DeviceRegistrationRequest;
import com.example.miniproject.entity.Device;
import com.example.miniproject.entity.House;
import com.example.miniproject.entity.Room;
import com.example.miniproject.exception.HouseNotFoundException;
import com.example.miniproject.exception.RoomNotFoundException;
import com.example.miniproject.repository.DeviceRepository;
import com.example.miniproject.repository.HouseRepository;
import com.example.miniproject.repository.InventoryRepository;
import com.example.miniproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    RoomRepository roomRepository;
    public Device registerDevice(DeviceRegistrationRequest deviceRegistrationRequest) {
        Device device=new Device();
        device.setKickston_id(deviceRegistrationRequest.getKickstoneId());
        device.setDevice_username(deviceRegistrationRequest.getDeviceUsername());
        device.setDevice_password(deviceRegistrationRequest.getDevicePassword());
        return deviceRepository.save(device);
    }
    public ResponseEntity<String> addDeviceToHouse(AddDeviceToHouseRequest request) throws RoomNotFoundException, HouseNotFoundException {
        House house = houseRepository.findById(Integer.parseInt(request.getHouseId()))
                .orElseThrow(() -> new HouseNotFoundException("House with given id , not found"));

        Room room = roomRepository.findById(Long.parseLong(request.getRoomId()))
                .orElseThrow(() -> new RoomNotFoundException("Room not found for the given id "));


        Device device = new Device();
        device.setHouse(house);
        device.setRoom(room);
        deviceRepository.save(device);
        return ResponseEntity.ok("device added successfully");
    }

    public boolean isValidDeviceCredentials(DeviceRegistrationRequest deviceDTO) {
        return deviceDTO != null &&
                isValidKickstoneId(deviceDTO.getKickstoneId()) &&
                isValidDeviceUsernameAndPassword(deviceDTO.getDeviceUsername(), deviceDTO.getDevicePassword());
    }

    private boolean isValidKickstoneId(String kickstoneId) {
        return inventoryRepository.existsById(kickstoneId);
    }

    private boolean isValidDeviceUsernameAndPassword(String username, String password) {
        return username != null && password != null && !username.isEmpty() && !password.isEmpty();
    }
}
