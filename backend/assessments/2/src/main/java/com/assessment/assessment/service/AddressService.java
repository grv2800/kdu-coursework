package com.assessment.assessment.service;

import com.assessment.assessment.entity.Address;
import com.assessment.assessment.repository.AddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class AddressService {
    @Autowired
    AddressRepo addressRepo;
    public void AddAddress(Address address){
        log.info("address added");
        addressRepo.save(address);
    }
}
