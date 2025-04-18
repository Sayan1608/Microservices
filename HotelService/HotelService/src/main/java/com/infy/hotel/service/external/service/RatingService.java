package com.infy.hotel.service.external.service;

import com.infy.hotel.service.dto.RatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {
    @GetMapping("/ratings/hotel/{hotelId}")
    List<RatingDTO> getHotelRatings(@PathVariable(name = "hotelId") String hotelId);
}
