package com.JPA.JPA.service;

import com.JPA.JPA.entity.Shift;
import com.JPA.JPA.repository.ShiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepo shiftRepository;

    public Shift createShift(Shift shift)
    {
        return shiftRepository.save(shift);
    }

    public List<Shift> findTop3Shifts(LocalDate startDate, LocalDate endDate) {
        return shiftRepository.findTop3ByStartDateAndEndDate(startDate, endDate);
    }
}

