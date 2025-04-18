package com.infy.hotel.service.controller;

import com.infy.hotel.service.dto.HotelDTO;
import com.infy.hotel.service.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelDTO> saveNewHotel(@RequestBody HotelDTO hotelDTO){
        HotelDTO savedHotel = hotelService.saveNewHotel(hotelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels(){
        List<HotelDTO> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    @GetMapping(path = "/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable(name = "hotelId") String hotelId){
        HotelDTO hotelById = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelById);
    }

    @PutMapping(path = "/{hotelId}")
    public ResponseEntity<HotelDTO> updateHotelDetails(@PathVariable(name = "hotelId") String hotelId,
                                                       @RequestBody HotelDTO hotelDTO){
        HotelDTO updatedHotel = hotelService.updateHotelDetails(hotelId, hotelDTO);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping(path = "/{hotelId}")
    public ResponseEntity<?> deleteHotelDetails(@PathVariable(name = "hotelId") String hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel with Id : " + hotelId + " deleted successfully!");
    }
}
