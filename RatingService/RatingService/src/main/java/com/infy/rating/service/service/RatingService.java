package com.infy.rating.service.service;

import com.infy.rating.service.dto.RatingDTO;

import java.util.List;

public interface RatingService {
    RatingDTO createRating(RatingDTO ratingDTO);
    List<RatingDTO> getAllRatings();
    List<RatingDTO> getRatingsByUserId(String userId);
    List<RatingDTO> getRatingsByHotelId(String hotelId);
}
