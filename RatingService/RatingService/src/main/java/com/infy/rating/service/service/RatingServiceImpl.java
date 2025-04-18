package com.infy.rating.service.service;

import com.infy.rating.service.dto.RatingDTO;
import com.infy.rating.service.entity.Rating;
import com.infy.rating.service.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {
        Rating savedRating = ratingRepository.save(modelMapper.map(ratingDTO, Rating.class));
        return modelMapper.map(savedRating,RatingDTO.class);
    }

    @Override
    public List<RatingDTO> getAllRatings() {
        List<Rating> allRatings = ratingRepository.findAll();
        return getRatingDTOS(allRatings);
    }

    private List<RatingDTO> getRatingDTOS(List<Rating> allRatings) {
        return allRatings
                .stream()
                .map(rating -> modelMapper.map(rating, RatingDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDTO> getRatingsByUserId(String userId) {
        List<Rating> ratingsList = ratingRepository.findByUserId(userId);
        return getRatingDTOS(ratingsList);
    }

    @Override
    public List<RatingDTO> getRatingsByHotelId(String hotelId) {
        List<Rating> byHotelId = ratingRepository.findByHotelId(hotelId);
        return getRatingDTOS(byHotelId);
    }
}
