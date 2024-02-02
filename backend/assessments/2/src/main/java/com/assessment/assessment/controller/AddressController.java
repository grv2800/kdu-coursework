package com.assessment.assessment.controller;

import com.assessment.assessment.entity.Address;
import com.assessment.assessment.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping("/addAddress")
    public ResponseEntity<String> addAdress(Address address){
        addressService.AddAddress(address);
        return new ResponseEntity<>("address added", HttpStatus.CREATED);
    }
}
