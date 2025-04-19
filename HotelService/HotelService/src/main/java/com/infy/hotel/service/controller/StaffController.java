package com.infy.hotel.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/staffs")
public class StaffController {
    @GetMapping
    public ResponseEntity<List<String>> getHotelStaffs(){
        List<String> staffList = List.of("Rita", "Sanjay", "Vicky", "Rohan");
        return ResponseEntity.ok(staffList);
    }
}
