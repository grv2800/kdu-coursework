package com.JPA.JPA.controller;

import com.JPA.JPA.entity.ShiftType;
import com.JPA.JPA.service.ShiftTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ShiftTypeController {
    @Autowired
    private ShiftTypeService shiftTypeService;

    @PostMapping("/postShiftType")
    public ShiftType createShiftType(@RequestBody ShiftType shiftType) {
        log.info("object of type ShiftType added");
        return shiftTypeService.createShiftType(shiftType);
    }
}