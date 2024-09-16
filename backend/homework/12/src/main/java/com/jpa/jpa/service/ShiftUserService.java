package com.JPA.JPA.service;

import com.JPA.JPA.entity.Shift;
import com.JPA.JPA.entity.ShiftUser;
import com.JPA.JPA.exception.UserNotFoundException;
import com.JPA.JPA.repository.ShiftUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
public class ShiftUserService {
    @Autowired
    private ShiftUserRepo shiftUserRepository;

    public ShiftUser createShiftUser(ShiftUser shiftUser) {
        return shiftUserRepository.save(shiftUser);
    }
    public void deleteShiftUser(UUID shiftUserId)throws UserNotFoundException{
        Optional<ShiftUser> optionalShiftUser = shiftUserRepository.findById(shiftUserId);
        if(optionalShiftUser.isEmpty()) {
            log.info("user not found");
            throw new UserNotFoundException("User not found");
        }
        ShiftUser shiftUser=optionalShiftUser.get();
        Shift shift=shiftUser.getShift();
        if(shift.getTimeEnd().equals(LocalTime.of(23,0))){
            shiftUserRepository.delete(shiftUser);
        }
        else throw new UserNotFoundException("invalid request, user not found");
    }
}

