package com.infy.hotel.service.service;

import com.infy.hotel.service.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    HotelDTO saveNewHotel(HotelDTO hotelDTO);
    List<HotelDTO> getAllHotels();
    HotelDTO getHotelById(String hotelId);
    HotelDTO updateHotelDetails(String hotelId, HotelDTO hotelDTO);
    void deleteHotel(String hotelId);
}
