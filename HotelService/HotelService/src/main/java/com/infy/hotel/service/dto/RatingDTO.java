package com.infy.hotel.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDTO {
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
}
