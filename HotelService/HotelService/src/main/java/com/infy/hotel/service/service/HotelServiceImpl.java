package com.infy.hotel.service.service;

import com.infy.hotel.service.dto.HotelDTO;
import com.infy.hotel.service.dto.RatingDTO;
import com.infy.hotel.service.entity.Hotel;
import com.infy.hotel.service.exceptions.ResourceNotFoundException;
import com.infy.hotel.service.external.service.RatingService;
import com.infy.hotel.service.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService{
    private final HotelRepository hotelRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingService ratingService;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDTO saveNewHotel(HotelDTO hotelDTO) {
        String uuid = UUID.randomUUID().toString();
        hotelDTO.setHotelId(uuid);
        Hotel hotel = hotelRepository.saveAndFlush(modelMapper.map(hotelDTO, Hotel.class));
        return modelMapper.map(hotel,HotelDTO.class);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        List<Hotel> allHotels = hotelRepository.findAll();
        List<HotelDTO> hotelList = allHotels
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class)).collect(Collectors.toList());

        hotelList
                .forEach(hotelDTO -> {
                    List<RatingDTO> hotelRatings = getHotelRatings(hotelDTO.getHotelId());
                    hotelDTO.setUserRatings(hotelRatings);
                });
        return hotelList;
    }

    @Override
    public HotelDTO getHotelById(String hotelId) {
        existHotelById(hotelId);
        Optional<Hotel> existHotelById = hotelRepository.findById(hotelId);
        HotelDTO hotel = modelMapper
                .map(existHotelById.orElseThrow(() -> new RuntimeException("Something went wrong in getHotelById in HotelServiceImpl")), HotelDTO.class);

        List<RatingDTO> hotelRatings = getHotelRatings(hotelId);
        hotel.setUserRatings(hotelRatings);
        return hotel;
    }

    private void existHotelById(String hotelId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel with Id : " + hotelId + " not found!");
        }
    }

    @Override
    public HotelDTO updateHotelDetails(String hotelId, HotelDTO hotelDTO) {
        existHotelById(hotelId);
        hotelDTO.setHotelId(hotelId);
        Hotel updatedHotel = hotelRepository.saveAndFlush(modelMapper.map(hotelDTO, Hotel.class));
        return modelMapper.map(updatedHotel,HotelDTO.class);
    }

    @Override
    public void deleteHotel(String hotelId) {
        existHotelById(hotelId);
        hotelRepository.deleteById(hotelId);
    }

    public List<RatingDTO> getHotelRatings(String hotelId){
        String url = "http://RATINGSERVICE/ratings/hotel/"+hotelId;
        /*ResponseEntity<List<RatingDTO>> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>() {
                }
        );*/

        return ratingService.getHotelRatings(hotelId);
    }
}
