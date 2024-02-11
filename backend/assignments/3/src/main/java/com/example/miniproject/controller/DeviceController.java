package com.example.miniproject.controller;

import com.example.miniproject.dto.request.AddDeviceToHouseRequest;
import com.example.miniproject.dto.request.DeviceRegistrationRequest;
import com.example.miniproject.exception.HouseNotFoundException;
import com.example.miniproject.exception.RoomNotFoundException;
import com.example.miniproject.service.AuthenticationService;
import com.example.miniproject.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {


    DeviceService deviceRegistrationService;
    AuthenticationService authenticationService;
    DeviceService deviceService;

    @Autowired
    DeviceController(DeviceService deviceRegistrationService,AuthenticationService authenticationService,
                     DeviceService deviceService){
        this.deviceRegistrationService=deviceRegistrationService;
        this.authenticationService=authenticationService;
        this.deviceService=deviceService;
    }
    @PostMapping("/api/v1/device/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceRegistrationRequest deviceDTO,
                                                      @RequestHeader(value = "Authorization") String authorization) {
        try {
            if (!isValidToken(authorization)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Unauthorized access");
            }

            if (!isValidDeviceCredentials(deviceDTO)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid device credentials");
            }

            deviceRegistrationService.registerDevice(deviceDTO);
            String response = "Device registered successfully";
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String response = "Failed to register device";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/api/v1/device/add")
    public ResponseEntity<String> addDeviceToHouse(@RequestBody AddDeviceToHouseRequest request) throws RoomNotFoundException, HouseNotFoundException {
        deviceService.addDeviceToHouse(request);
        return ResponseEntity.ok()
                .body("Device added successfully");
    }

    private boolean isValidToken(String authorization) {

        String token = extractToken(authorization);
        return authenticationService.isValidToken(token);
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private boolean isValidDeviceCredentials(DeviceRegistrationRequest deviceDTO) {
        return deviceService.isValidDeviceCredentials(deviceDTO);
    }
}
