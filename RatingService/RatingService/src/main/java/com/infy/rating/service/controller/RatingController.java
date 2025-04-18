package com.infy.rating.service.controller;

import com.infy.rating.service.dto.RatingDTO;
import com.infy.rating.service.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO){
        RatingDTO rating = ratingService.createRating(ratingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings(){
        List<RatingDTO> allRatings = ratingService.getAllRatings();
        return ResponseEntity.ok(allRatings);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByUserId(@PathVariable(name = "userId") String userId){
        List<RatingDTO> ratingsByUserId = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratingsByUserId);
    }

    @GetMapping(path = "/hotel/{hotelId}")
    public ResponseEntity<List<RatingDTO>> getRatingsByHotelId(@PathVariable(name = "hotelId") String hotelId){
        List<RatingDTO> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.ok(ratingsByHotelId);
    }

}
