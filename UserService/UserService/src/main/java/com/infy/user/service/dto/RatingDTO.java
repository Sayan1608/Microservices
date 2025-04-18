package com.infy.user.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDTO {
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
}
