package com.JPA.JPA.service;

import com.JPA.JPA.entity.ShiftType;
import com.JPA.JPA.repository.ShiftTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftTypeService {
    @Autowired
    private ShiftTypeRepo shiftTypeRepository;

    public ShiftType createShiftType(ShiftType shiftType) {
        return shiftTypeRepository.save(shiftType);
    }
}
