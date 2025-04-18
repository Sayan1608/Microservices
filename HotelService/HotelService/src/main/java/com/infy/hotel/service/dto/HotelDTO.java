package com.infy.hotel.service.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {
    private String hotelId;
    private String hotelName;
    private String hotelLocation;
    private String about;
    private List<RatingDTO> userRatings=new ArrayList<>();
}
