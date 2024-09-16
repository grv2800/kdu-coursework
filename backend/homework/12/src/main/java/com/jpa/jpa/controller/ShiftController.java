package com.JPA.JPA.controller;

import com.JPA.JPA.entity.Shift;
import com.JPA.JPA.service.ShiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping("/postShift")
    public Shift createShift(@RequestBody Shift shift) {
        log.info("object of type Shift added");
        return shiftService.createShift(shift);
    }
    @GetMapping("/top3users")
    public ResponseEntity<List<Shift>> findTop3Shifts() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 25);

        List<Shift> top3Shifts = shiftService.findTop3Shifts(startDate, endDate);

        return ResponseEntity.ok(top3Shifts);
    }

}
