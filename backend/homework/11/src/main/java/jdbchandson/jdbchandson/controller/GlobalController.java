package jdbchandson.jdbchandson.controller;

import jdbchandson.jdbchandson.dto.*;
import jdbchandson.jdbchandson.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@Data
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    ShiftService shiftService;
    ShiftTypeService shiftTypeService;
    UserService userService;
    AllEntitiesService allEntitiesService;
    ShiftUserService shiftUserService;
    @Autowired
    GlobalController(ShiftService shiftService,ShiftTypeService shiftTypeService,UserService userService,AllEntitiesService allEntitiesService,
                     ShiftUserService shiftUserService){
        this.shiftService=shiftService;
        this.shiftTypeService=shiftTypeService;
        this.userService=userService;
        this.allEntitiesService=allEntitiesService;
        this.shiftUserService=shiftUserService;

    }

    @PostMapping("/save-shift")
    public ResponseEntity<String> saveShift(@RequestBody ShiftDto shiftDto) {
        try {
            shiftService.saveShift(shiftDto);
            log.info("Shift saved successfully");
            return ResponseEntity.ok("Shift saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Shift");
        }
    }

    @GetMapping("get-shift/{tenantID}")
    public ResponseEntity<String> getByTenant(@PathVariable UUID tenantID) {
        try {
            log.info("shift fetched successfully");
            List<ShiftDto> shiftDTO = shiftService.getShift(tenantID);
            if (shiftDTO!=null) {
                return ResponseEntity.ok(shiftDTO.toString());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch shift");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAllEntities(@RequestBody AllEntitiesDto allEntitiesDTO) {
        try {
            allEntitiesService.saveAllEntities(allEntitiesDTO);
            return ResponseEntity.ok("All entities saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving entities");
        }
    }

    @PostMapping("/saveShiftType")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftTypeDto shiftTypeDto) {
        try {
            shiftTypeService.saveShiftType(shiftTypeDto);
            return ResponseEntity.ok("ShiftType saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving ShiftType");
        }
    }


    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        try {
            userService.saveUser(userDto);
            return ResponseEntity.ok("User saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving User");
        }
    }

    @PostMapping("/saveShiftUser")
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUserDto shiftUserDto) {
        try {
            shiftUserService.saveShiftUser(shiftUserDto);
            return ResponseEntity.ok("ShiftUser saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving ShiftUser");
        }
    }
}
