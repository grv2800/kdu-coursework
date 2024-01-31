package com.JPA.JPA.controller;

import com.JPA.JPA.entity.ShiftUser;
import com.JPA.JPA.exception.UserNotFoundException;
import com.JPA.JPA.service.ShiftUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class ShiftUserController {
    @Autowired
    private ShiftUserService shiftUserService;

    @PostMapping("/postShiftUser")
    public ShiftUser createShiftUser(@RequestBody ShiftUser shiftUser) {
        log.info("object of type shiftUser added");
        return shiftUserService.createShiftUser(shiftUser);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id)throws UserNotFoundException {
        try {
            shiftUserService.deleteShiftUser(id);
            return ResponseEntity.ok("Shift user with ID " + id + " deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
